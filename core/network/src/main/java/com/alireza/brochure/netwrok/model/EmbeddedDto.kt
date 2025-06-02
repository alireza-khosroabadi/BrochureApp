package com.alireza.brochure.netwrok.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmbeddedDto(
    @SerialName("contents")
    val contents: List<ContentDto>? = listOf()
)