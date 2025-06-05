package com.alireza.brochure.database.entity

import androidx.room.ColumnInfo

data class StoreLocationEntity(
    val city: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val street: String = "",
    @ColumnInfo(name = "street_number") val streetNumber: String = "",
    @ColumnInfo("zip_code") val zipCode: String = ""
)
