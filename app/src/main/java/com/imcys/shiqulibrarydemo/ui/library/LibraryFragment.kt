package com.imcys.shiqulibrarydemo.ui.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.imcys.shiqulibrarydemo.adaprer.ArticleDifficultAdapter
import com.imcys.shiqulibrarydemo.adaprer.LibraryArticleAdapter
import com.imcys.shiqulibrarydemo.adaprer.LibraryArticleTypeAdapter
import com.imcys.shiqulibrarydemo.base.BaseFragment
import com.imcys.shiqulibrarydemo.databinding.FragmentLibraryBinding
import com.imcys.shiqulibrarydemo.model.LibraryArticleLisData
import com.imcys.shiqulibrarydemo.weight.ArticleRefreshFooter
import com.imcys.shiqulibrarydemo.weight.ArticleRefreshHeader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * 文库
 * 主要显示所有的书籍
 */
class LibraryFragment : BaseFragment<FragmentLibraryBinding>() {

    private val viewModel: LibraryViewModel by viewModel()
    private val articleTypeAdapter = LibraryArticleTypeAdapter()
    private val articleDifficultAdapter = ArticleDifficultAdapter()
    private val articleAdapter = LibraryArticleAdapter()
    private var selectedTypeId: Int = 0
    private var selectedDifficult: Int = 600
    private var currentPageInfo: LibraryArticleLisData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getViewBinding(): FragmentLibraryBinding =
        FragmentLibraryBinding.inflate(layoutInflater)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, systemBars.top, 0, 0)
            insets
        }
        // 初始化视图
        initView()
        // 绑定数据
        bindData()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    /**
     * 绑定数据
     * 监听StateFlow并且更新界面
     */
    private fun bindData() {
        // 文章类型列表
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.articleTypeList.collect {
                    articleTypeAdapter.dataList = it
                    articleTypeAdapter.notifyDataSetChanged()
                }
            }
        }
        // 文章难度列表
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.articleDifficultData.collect {
                    articleDifficultAdapter.dataList = it
                    articleDifficultAdapter.notifyDataSetChanged()
                }
            }
        }

        // 文章列表
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.articleList.collect {
                    it?.let { data ->
                        currentPageInfo = data
                        articleAdapter.submitList(data.list)
                        if (currentPageInfo?.hasNextPage != true) {
                            // 阻断加载
                            binding.libraryArticleRefreshLayout.finishLoadMoreWithNoMoreData()
                        } else {
                            // 解除还有更多数据
                            binding.libraryArticleRefreshLayout.setNoMoreData(false)
                        }

                    }
                }
            }
        }

        // 加载/刷新状态
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isRefresh.collect {
                    if (!it) {
                        binding.libraryArticleRefreshLayout.finishRefresh()
                        binding.libraryArticleRefreshLayout.finishLoadMore()
                    }
                }
            }
        }

        // 困难度选择
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.selectedDifficult.collect {
                    it?.let {
                        // 更新困难度
                        selectedDifficult = it
                        // 更新库难度选择
                        val newIndex = articleDifficultAdapter.dataList.indexOf(it)
                        val oldIndex = articleDifficultAdapter.selectIndex
                        articleDifficultAdapter.selectIndex = newIndex
                        // 移动显示位置
                        binding.libraryArticleDifficultRv.scrollToPosition(newIndex)
                        articleDifficultAdapter.notifyItemChanged(oldIndex)
                        articleDifficultAdapter.notifyItemChanged(newIndex)
                        // 刷新文章列表
                        articleAdapter.submitList(listOf())
                        viewModel.loadArticleList(selectedDifficult, selectedTypeId,isClear = true)
                    }
                }
            }
        }

    }

    private fun initView() {
        // 初始化文章类型列表
        initArticleTypeList()
        // 初始化文章难度列表
        initArticleDifficultList()
        // 初始化文章列表
        initArticleList()
    }

    /**
     * 初始化文章列表，至只是将适配器和布局绑定
     */
    private fun initArticleList() {
        binding.apply {
            libraryArticleRv.adapter = articleAdapter
            libraryArticleRv.layoutManager = LinearLayoutManager(requireContext())

            libraryArticleRefreshLayout.setRefreshHeader(ArticleRefreshHeader(context))
            libraryArticleRefreshLayout.setRefreshFooter(ArticleRefreshFooter(context))
            libraryArticleRefreshLayout.setOnRefreshListener { refreshlayout ->
                // 请求刷新
                lifecycleScope.launch(Dispatchers.IO) {
                    viewModel.refreshArticle(
                        lexile = selectedDifficult,
                        typeId = selectedTypeId,
                        page =  1,
                        size =  10,
                        isClear = true,
                    )
                }
            }

            libraryArticleRefreshLayout.setEnableLoadMoreWhenContentNotFull(false)//取消内容不满一页时开启上拉加载功能
            libraryArticleRefreshLayout.setOnLoadMoreListener {
                // 请求加载更多
                lifecycleScope.launch(Dispatchers.IO) {
                    if (currentPageInfo?.hasNextPage == true) {
                        viewModel.refreshArticle(
                            lexile = selectedDifficult,
                            typeId = selectedTypeId,
                            page = currentPageInfo?.nextPage ?: 1,
                            size = currentPageInfo?.size ?: 10,
                        )
                    }
                }
            }
        }
    }

    /**
     * 初始化文章难度列表，至只是将适配器和布局绑定
     */
    private fun initArticleDifficultList() {
        binding.apply {
            libraryArticleDifficultRv.adapter = articleDifficultAdapter
            articleDifficultAdapter.onItemClick = {
                // 点击了文章难度
                // 加载文章列表
                selectedDifficult = it
                articleAdapter.submitList(listOf())
                viewModel.loadArticleList(selectedDifficult, selectedTypeId,isClear = true)
            }
            libraryArticleDifficultRv.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        }
    }

    /**
     * 初始化文章类型列表，至只是将适配器和布局绑定
     */
    private fun initArticleTypeList() {
        binding.apply {
            libraryArticleTypeRv.adapter = articleTypeAdapter
            articleTypeAdapter.onItemClick = {
                // 点击了文章类型
                // 加载文章列表
                selectedTypeId = it.id
                articleAdapter.submitList(listOf())
                viewModel.loadArticleList(selectedDifficult, selectedTypeId,isClear = true)
            }
            libraryArticleTypeRv.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            LibraryFragment()
    }
}