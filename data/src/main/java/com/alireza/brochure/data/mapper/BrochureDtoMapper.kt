package com.alireza.brochure.data.mapper

import com.alireza.brochure.model.brochure.PremiumBrochure
import com.alireza.brochure.model.brochure.RegularBrochure
import com.alireza.brochure.model.brochure.SuperBannerContent
import com.alireza.brochure.netwrok.model.BrochureDto
import com.alireza.brochure.netwrok.model.SuperBannerCarouselDto

fun BrochureDto.toBrochureDomainModel(): RegularBrochure = RegularBrochure(
        id = contentId.orEmpty(),
        name = title.orEmpty(),
        distance = distance?:0.0,
        imageUrl = brochureImage.orEmpty(),
)

fun BrochureDto.toBrochurePremiumDomainModel(): PremiumBrochure = PremiumBrochure(
        id = contentId.orEmpty(),
        name = title.orEmpty(),
        distance = distance?:0.0,
        imageUrl = brochureImage.orEmpty(),
)

fun SuperBannerCarouselDto.toSuperBannerCarousel(): SuperBannerContent =  SuperBannerContent(
    id = id.orEmpty(),
    publisherId = publisher?.id.orEmpty(),
    publishedFrom= publishedFrom.orEmpty(),
    publishedUntil = publishedUntil.orEmpty(),
    imageUrl = imageUrl.orEmpty(),
    clickUrl = clickUrl.orEmpty()
)