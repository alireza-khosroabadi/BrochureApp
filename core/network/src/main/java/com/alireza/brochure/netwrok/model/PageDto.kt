package com.alireza.brochure.netwrok.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageDto(
    @SerialName("number")
    val number: Int? = null,
    @SerialName("size")
    val size: Int? = null,
    @SerialName("totalElements")
    val totalElements: Int? = null,
    @SerialName("totalPages")
    val totalPages: Int? = null
)