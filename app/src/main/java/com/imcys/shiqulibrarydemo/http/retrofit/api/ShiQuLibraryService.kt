package com.imcys.shiqulibrarydemo.http.retrofit.api

import com.imcys.shiqulibrarydemo.model.ArticleTypeData
import com.imcys.shiqulibrarydemo.model.ApiResponse
import com.imcys.shiqulibrarydemo.model.AppArticleDifficultData
import com.imcys.shiqulibrarydemo.model.LibraryArticleLisData
import retrofit2.http.GET
import retrofit2.http.Query

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
    suspend fun getAppArticleDifficultList(): ApiResponse<ArrayList<Int>>

    /**
     * 获取文章列表
     * @param lexile 难度
     * @param typeId 类型
     * @param page 页码
     * @param size 每页大小
     */
    @GET("englishgpt/library/articleList")
    suspend fun getArticleList(
        @Query("lexile") lexile: Int,
        @Query("typeId") typeId: Int = 0,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10
    ): ApiResponse<LibraryArticleLisData>

}