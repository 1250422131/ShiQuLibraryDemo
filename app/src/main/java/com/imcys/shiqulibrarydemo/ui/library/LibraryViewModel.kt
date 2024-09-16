package com.imcys.shiqulibrarydemo.ui.library

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imcys.shiqulibrarydemo.http.retrofit.api.ShiQuLibraryService
import com.imcys.shiqulibrarydemo.model.AppArticleDifficultData
import com.imcys.shiqulibrarydemo.model.ArticleTypeData
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

    init {
        loadArticleTypeList()
        loadArticleDifficultList()
    }

    fun loadArticleDifficultList(){
        viewModelScope.launch {
            requestApi {
                shiQuLibraryService.getAppArticleDifficultList()
            }.apply {
                if (code == 200) {
                    data?.let { _articleDifficultData.emit(it)}
                } else {
                    // 请求失败
                    Log.e("TAG", "loadArticleTypeList: ${this.msg}")
                }
            }
        }
    }

    fun loadArticleTypeList() {
        viewModelScope.launch {
            requestApi {
                shiQuLibraryService.getArticleTypeList()
            }.apply {
                if (code == 200) {
                    data?.let { _articleTypeList.emit(it)}
                } else {
                    // 请求失败
                    Log.e("TAG", "loadArticleTypeList: ${this.msg}")
                }
            }
        }
    }

}