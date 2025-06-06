package com.alireza.brochure.model.brochureDetail

data class StoreLocation(
    val city: String,
    val latitude: Double,
    val longitude: Double,
    val street: String,
    val streetNumber: String,
    val zipCode: String
)
