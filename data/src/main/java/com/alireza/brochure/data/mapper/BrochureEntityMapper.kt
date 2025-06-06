package com.alireza.brochure.data.mapper

import com.alireza.brochure.database.entity.BaseBrochure
import com.alireza.brochure.database.entity.BrochureEntity
import com.alireza.brochure.database.entity.SuperBannerEntity
import com.alireza.brochure.model.brochure.BrochureModel
import com.alireza.brochure.model.brochure.PremiumBrochure
import com.alireza.brochure.model.brochure.RegularBrochure
import com.alireza.brochure.model.brochure.SuperBannerCarousel
import com.alireza.brochure.model.brochure.SuperBannerContent
import com.alireza.brochure.model.brochureDetail.BrochureDetail

fun BaseBrochure.toBrochureModel(): BrochureModel {
    return when (this) {
        is BaseBrochure.BrochureWrapperEntity -> when (this.entity.type) {
            "brochure" -> this.entity.toRegularBrochure()
            "brochurePremium" -> this.entity.toPremiumBrochure()
            else -> this.entity.toRegularBrochure()
        }

        is BaseBrochure.SuperBannerWrapperEntity -> SuperBannerCarousel(this.entity.map { it.toSuperBannerCarousel() })
    }
}

fun BrochureEntity.toRegularBrochure(): BrochureModel = RegularBrochure(
    id = contentId,
    name = title,
    distance = distance,
    imageUrl = imageUrl
)

fun BrochureEntity.toPremiumBrochure(): BrochureModel = PremiumBrochure(
    id = contentId,
    name = title,
    distance = distance,
    imageUrl = imageUrl
)

fun SuperBannerEntity.toSuperBannerCarousel(): SuperBannerContent = SuperBannerContent(
    id = id,
    publishedFrom = publishedFrom,
    publishedUntil = publishedUntil,
    clickUrl = clickUrl,
    imageUrl = imageUrl
)


fun BrochureEntity.toBrochureDetail():BrochureDetail =
   BrochureDetail(
       id = contentId,
       title = title,
       distance = distance,
       type = type,
       imageUrl = imageUrl,
       publishedFrom = DateMapper.formatDate(publishedFrom),
       publishedUntil = DateMapper.formatDate(publishedUntil),
       validFrom = DateMapper.formatDate(validFrom),
       validUntil = DateMapper.formatDate(validUntil),
       orderIndex = orderIndex,
       storeLocation = storeLocation.toStoreLocation()
   )

