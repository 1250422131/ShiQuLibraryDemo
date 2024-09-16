package com.imcys.shiqulibrarydemo.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * 文章类型数据
 */
@Serializable
data class ArticleTypeData(
    @SerialName("id")
    val id: Int,
    @SerialName("type")
    val type: String
)