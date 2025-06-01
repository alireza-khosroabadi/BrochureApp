package com.alireza.brochure.netwrok.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class ContentDto(
    @SerialName("adFormat")
    val adFormat: String? = "",
    @SerialName("content")
    val content: JsonElement? = null,
    @SerialName("contentFormatSource")
    val contentFormatSource: String? = "",
    @SerialName("contentType")
    val contentType: String? = "",
    @SerialName("externalTracking")
    val externalTracking: ExternalTrackingDto? = ExternalTrackingDto(),
    @SerialName("placement")
    val placement: String? = ""
)