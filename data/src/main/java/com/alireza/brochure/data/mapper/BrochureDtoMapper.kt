package com.alireza.brochure.data.mapper

import com.alireza.brochure.data.remote.dto.BrochureDto
import com.alireza.brochure.data.remote.dto.SuperBannerCarouselDto
import com.alireza.brochure.domain.model.brochure.PremiumBrochure
import com.alireza.brochure.domain.model.brochure.RegularBrochure
import com.alireza.brochure.domain.model.brochure.SuperBannerContent

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