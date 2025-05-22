package com.alireza.brochure.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalTrackingDto(
    @SerialName("click")
    val click: List<String>? = null,
    @SerialName("impression")
    val impression: List<String>? = null
)