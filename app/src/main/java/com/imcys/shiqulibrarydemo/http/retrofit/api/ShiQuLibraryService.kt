package com.imcys.shiqulibrarydemo.http.retrofit.api

import com.imcys.shiqulibrarydemo.model.ArticleTypeData
import com.imcys.shiqulibrarydemo.model.ApiResponse
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
}