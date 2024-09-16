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
import com.imcys.shiqulibrarydemo.adaprer.LibraryArticleTypeAdapter
import com.imcys.shiqulibrarydemo.base.BaseFragment
import com.imcys.shiqulibrarydemo.databinding.FragmentLibraryBinding
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
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.articleTypeList.collect {
                    articleTypeAdapter.dataList = it
                    articleTypeAdapter.notifyDataSetChanged()
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.articleDifficultData.collect {
                    articleDifficultAdapter.dataList = it
                    articleDifficultAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun initView() {
        initArticleTypeList()
        initArticleDifficultList()
    }

    private fun initArticleDifficultList() {
        binding.apply {
            libraryArticleDifficultRv.adapter = articleDifficultAdapter
            articleDifficultAdapter.onItemClick = {

            }
            libraryArticleDifficultRv.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        }
    }

    private fun initArticleTypeList() {
        binding.apply {
            libraryArticleTypeRv.adapter = articleTypeAdapter
            libraryArticleTypeRv.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            LibraryFragment()
    }
}