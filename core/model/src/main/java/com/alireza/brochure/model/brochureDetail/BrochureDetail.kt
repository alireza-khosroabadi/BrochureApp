package com.alireza.brochure.model.brochureDetail

data class BrochureDetail(
    val id: String,
    val title: String,
    val distance: Double,
    val type: String,
    val imageUrl: String,
    val publishedFrom: String,
    val publishedUntil: String,
    val validFrom: String,
    val validUntil: String,
    val orderIndex: Int,
    val storeLocation: StoreLocation
){
    val isPremium: Boolean
        get() = type == "Premium"
}
