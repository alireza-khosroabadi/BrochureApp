package com.alireza.brochure.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinksDto(
    @SerialName("self")
    val self: SelfDto? = null
)