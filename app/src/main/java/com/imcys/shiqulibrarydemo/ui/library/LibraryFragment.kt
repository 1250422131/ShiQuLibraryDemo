package com.imcys.shiqulibrarydemo.ui.library

import android.R
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
import com.imcys.shiqulibrarydemo.weight.ArticleRefreshHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator
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
    private var selectedDifficult: Int = 5
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
                        articleAdapter.dataList = data.list
                        articleAdapter.notifyDataSetChanged()
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
     * 初始化文章列表
     */
    private fun initArticleList() {
        binding.apply {
            libraryArticleRv.adapter = articleAdapter
            libraryArticleRv.layoutManager = LinearLayoutManager(requireContext())

            //设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator(DefaultRefreshHeaderCreator { context, layout ->
                ArticleRefreshHeader(context)
            })
            refreshLayout.setOnRefreshListener { refreshlayout ->
                refreshlayout.finishRefresh(2000 /*,false*/) //传入false表示刷新失败
            }
        }
    }

    /**
     * 初始化文章难度列表
     */
    private fun initArticleDifficultList() {
        binding.apply {
            libraryArticleDifficultRv.adapter = articleDifficultAdapter
            articleDifficultAdapter.onItemClick = {
                // 点击了文章难度
                // 加载文章列表
                selectedDifficult = it
                viewModel.loadArticleList(selectedDifficult, selectedTypeId)
            }
            libraryArticleDifficultRv.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        }
    }

    /**
     * 初始化文章类型列表
     */
    private fun initArticleTypeList() {
        binding.apply {
            libraryArticleTypeRv.adapter = articleTypeAdapter
            articleTypeAdapter.onItemClick = {
                // 点击了文章类型
                // 加载文章列表
                selectedTypeId = it.id
                viewModel.loadArticleList(selectedDifficult, selectedTypeId)
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