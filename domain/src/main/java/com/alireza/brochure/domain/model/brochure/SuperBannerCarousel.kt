package com.alireza.brochure.domain.model.brochure

data class SuperBannerContent(
    val id: String,
    val publisherId: String,
    val publishedFrom: String,
    val publishedUntil: String,
    val clickUrl: String,
    val imageUrl: String
)
