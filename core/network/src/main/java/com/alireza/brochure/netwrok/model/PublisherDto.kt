package com.alireza.brochure.netwrok.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PublisherDto(
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("type")
    val type: String? = null
)