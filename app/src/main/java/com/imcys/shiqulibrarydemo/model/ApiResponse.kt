package com.imcys.shiqulibrarydemo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
 data class ApiResponse<T>(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val data: T?,
    @SerialName("msg")
    val msg: String
)