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

class LibraryViewModel(
    private val shiQuLibraryService: ShiQuLibraryService
) : ViewModel() {

    private val _articleTypeList = MutableStateFlow<List<ArticleTypeData>>(listOf())
    val articleTypeList = _articleTypeList.asStateFlow()

    private val _articleDifficultData = MutableStateFlow(listOf<Int>())
    val articleDifficultData = _articleDifficultData.asStateFlow()

    private val _articleList = MutableStateFlow<LibraryArticleLisData?>(null)
    val articleList = _articleList.asStateFlow()

    private val _isRefresh = MutableStateFlow(false)
    val isRefresh = _isRefresh.asStateFlow()

    private val _selectedDifficult = MutableStateFlow<Int?>(null)
    val selectedDifficult = _selectedDifficult.asStateFlow()

    init {

        viewModelScope.launch {
            loadArticleTypeList()
            loadArticleDifficultList()
            // 从存储层获取难度
            loadDifficult()
        }

    }

    /**
     * 更改难度
     */
    private fun loadDifficult(lexile: Int = 600) {
        viewModelScope.launch {
            _selectedDifficult.emit(lexile)
        }
    }

    /**
     * 刷新/加载文章列表
     * @param lexile 难度
     * @param typeId 类型
     * @param page 页码
     * @param size 每页大小
     * @param isClear 是否清空列表
     */
    fun refreshArticle(
        lexile: Int,
        typeId: Int = 0,
        page: Int = 1,
        size: Int = 10,
        isClear: Boolean = false,
    ) {
        viewModelScope.launch {
            _isRefresh.emit(true)
            loadArticleList(lexile, typeId, page, size, isClear)
        }
    }


    /**
     * 加载文章列表
     * @param lexile 难度
     * @param typeId 类型
     * @param page 页码
     * @param size 每页大小
     * @param isClear 是否清空列表
     */
    fun loadArticleList(
        lexile: Int, typeId: Int = 0, page: Int = 1, size: Int = 10, isClear: Boolean = false,
    ) {
        viewModelScope.launch {
            requestApi {
                shiQuLibraryService.getArticleList(lexile, typeId, page, size)
            }.apply {
                if (code == 200) {
                    data?.let {
                        // 让新的数据和旧的合并
                        val newItems = if (isClear) {
                            _articleList.emit(null)
                            it.list
                        } else {
                            (articleList.value?.list ?: listOf()) + it.list
                        }
                        _articleList.emit(it.copy(list = newItems))
                    }
                } else {
                    // 请求失败
                    Log.e("TAG", "loadArticleList: ${this.msg}")
                }
                _isRefresh.emit(false)
            }
        }
    }


    /**
     * 加载文章难度列表
     */
    private suspend fun loadArticleDifficultList() {
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

    /**
     * 加载文章类型列表
     */
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