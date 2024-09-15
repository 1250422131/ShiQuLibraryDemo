package com.imcys.shiqulibrarydemo.http.retrofit.api

import com.imcys.shiqulibrarydemo.model.ArticleTypeData
import com.imcys.shiqulibrarydemo.model.ApiResponse
import com.imcys.shiqulibrarydemo.model.AppArticleDifficultData
import retrofit2.http.GET

/**
 * 书库的API
 */
interface ShiQuLibraryService {

    /**
     * 获取文章类型列表
     */
    @GET("englishgpt/library/articleTypeList")
    suspend fun getArticleTypeList(): ApiResponse<List<ArticleTypeData>>

    /**
     * 获取文章难度列表
     */
    @GET("englishgpt/appArticle/selectList")
    suspend fun getAppArticleDifficultList(): ApiResponse<AppArticleDifficultData>

}