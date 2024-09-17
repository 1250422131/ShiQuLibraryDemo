package com.imcys.shiqulibrarydemo.ui.library

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imcys.shiqulibrarydemo.http.retrofit.api.ShiQuLibraryService
import com.imcys.shiqulibrarydemo.model.ArticleTypeData
import com.imcys.shiqulibrarydemo.model.LibraryArticleLisData
import com.imcys.shiqulibrarydemo.utils.extend.requestApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LibraryViewModel(private val shiQuLibraryService: ShiQuLibraryService) : ViewModel() {

    private val _articleTypeList = MutableStateFlow<List<ArticleTypeData>>(listOf())
    val articleTypeList = _articleTypeList.asStateFlow()

    private val _articleDifficultData = MutableStateFlow(listOf<Int>())
    val articleDifficultData = _articleDifficultData.asStateFlow()

    private val _articleList = MutableStateFlow<LibraryArticleLisData?>(null)
    val articleList = _articleList.asStateFlow()

    private val _isRefresh = MutableStateFlow(false)
    val isRefresh = _isRefresh.asStateFlow()

    init {
        loadArticleTypeList()
        loadArticleDifficultList()
        loadArticleList(600)
    }

    fun refreshArticle(
        lexile: Int,
        typeId: Int = 0,
        page: Int = 1,
        size: Int = 10,
    ) {
        viewModelScope.launch {
            _isRefresh.emit(true)
        }
        loadArticleList(lexile, typeId, page, size)
    }


    fun loadArticleList(lexile: Int, typeId: Int = 0, page: Int = 1, size: Int = 10) {
        viewModelScope.launch {
            requestApi {
                shiQuLibraryService.getArticleList(lexile, typeId, page, size)
            }.apply {
                if (code == 200) {
                    data?.let { _articleList.emit(it) }
                } else {
                    // 请求失败
                    Log.e("TAG", "loadArticleList: ${this.msg}")
                }
                _isRefresh.emit(false)
            }
        }
    }


    private fun loadArticleDifficultList() {
        viewModelScope.launch {
            requestApi {
                shiQuLibraryService.getAppArticleDifficultList()
            }.apply {
                if (code == 200) {
                    data?.let { _articleDifficultData.emit(it) }
                } else {
                    // 请求失败
                    Log.e("TAG", "loadArticleDifficultList: ${this.msg}")
                }
            }
        }
    }

    private fun loadArticleTypeList() {
        viewModelScope.launch {
            requestApi {
                shiQuLibraryService.getArticleTypeList()
            }.apply {
                if (code == 200) {
                    data?.let { _articleTypeList.emit(it) }
                } else {
                    // 请求失败
                    Log.e("TAG", "loadArticleTypeList: ${this.msg}")
                }
            }
        }
    }

}