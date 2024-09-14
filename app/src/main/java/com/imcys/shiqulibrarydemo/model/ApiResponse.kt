package com.imcys.shiqulibrarydemo.model

import kotlinx.serialization.SerialName

 class ApiResponse<T>(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val data: T?,
    @SerialName("msg")
    val msg: String
)