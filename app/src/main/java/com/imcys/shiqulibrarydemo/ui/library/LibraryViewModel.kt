package com.imcys.shiqulibrarydemo.ui.library

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imcys.shiqulibrarydemo.http.retrofit.api.ShiQuLibraryService
import com.imcys.shiqulibrarydemo.model.ArticleTypeData
import com.imcys.shiqulibrarydemo.utils.extend.requestApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LibraryViewModel(private val shiQuLibraryService: ShiQuLibraryService) : ViewModel() {

    private val _articleTypeList = MutableStateFlow<List<ArticleTypeData>>(listOf())
    val articleTypeList = _articleTypeList.asStateFlow()


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