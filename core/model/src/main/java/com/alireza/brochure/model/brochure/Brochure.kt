package com.alireza.brochure.model.brochure


interface BrochureModel

interface  Brochure{
    val id: String
    val distance: Double
}
    data class RegularBrochure(
        override val id: String,
        val name: String,
        override val distance: Double,
        val imageUrl: String,
    ):Brochure, BrochureModel

    data class PremiumBrochure(
        override val id: String,
        val name: String,
        override val distance: Double,
        val imageUrl: String
    ):Brochure, BrochureModel

    data class SuperBannerCarousel(val banner: List<SuperBannerContent>): BrochureModel
