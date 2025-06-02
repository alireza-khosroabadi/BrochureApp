package com.alireza.brochure.netwrok.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClosestStoreDto(
    @SerialName("city")
    val city: String? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("latitude")
    val latitude: Double? = null,
    @SerialName("longitude")
    val longitude: Double? = null,
    @SerialName("street")
    val street: String? = null,
    @SerialName("streetNumber")
    val streetNumber: String? = null,
    @SerialName("zip")
    val zip: String? = null
)