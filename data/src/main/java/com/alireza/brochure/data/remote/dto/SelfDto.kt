package com.alireza.brochure.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SelfDto(
    @SerialName("href")
    val href: String? = null
)