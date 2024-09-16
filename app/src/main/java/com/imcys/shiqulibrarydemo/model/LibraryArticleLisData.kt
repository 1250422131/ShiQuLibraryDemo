package com.imcys.shiqulibrarydemo.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * 文章列表数据
 */
@Serializable
data class LibraryArticleLisData(
    @SerialName("endRow")
    val endRow: Int,
    @SerialName("firstPage")
    val firstPage: Int,
    @SerialName("hasNextPage")
    val hasNextPage: Boolean,
    @SerialName("hasPreviousPage")
    val hasPreviousPage: Boolean,
    @SerialName("isFirstPage")
    val isFirstPage: Boolean,
    @SerialName("isLastPage")
    val isLastPage: Boolean,
    @SerialName("lastPage")
    val lastPage: Int,
    @SerialName("list")
    val list: List<Item>,
    @SerialName("navigateFirstPage")
    val navigateFirstPage: Int,
    @SerialName("navigateLastPage")
    val navigateLastPage: Int,
    @SerialName("navigatePages")
    val navigatePages: Int,
    @SerialName("navigatepageNums")
    val navigatepageNums: List<Int>,
    @SerialName("nextPage")
    val nextPage: Int,
    @SerialName("pageNum")
    val pageNum: Int?,
    @SerialName("pageSize")
    val pageSize: Int,
    @SerialName("pages")
    val pages: Int,
    @SerialName("prePage")
    val prePage: Int,
    @SerialName("size")
    val size: Int,
    @SerialName("startRow")
    val startRow: Int,
    @SerialName("total")
    val total: Int
) {
    @Serializable
    data class Item(
        @SerialName("accuracy")
        val accuracy: Int?,
        @SerialName("accuracyRatio")
        val accuracyRatio: Double?,
        @SerialName("clickRatio")
        val clickRatio: Double?,
        @SerialName("color")
        val color: String?,
        @SerialName("cover")
        val cover: String?,
        @SerialName("id")
        val id: Int?,
        @SerialName("isRead")
        val isRead: Int?,
        @SerialName("lexile")
        val lexile: Int?,
        @SerialName("readTime")
        val readTime: Int?,
        @SerialName("title")
        val title: String?,
        @SerialName("type")
        val type: String?,
        @SerialName("typeId")
        val typeId: Int?,
        @SerialName("wordNum")
        val wordNum: Int?
    )

}