package com.alireza.brochure.data.mapper

import com.alireza.brochure.database.entity.BaseBrochure
import com.alireza.brochure.database.entity.BrochureEntity
import com.alireza.brochure.database.entity.StoreLocationEntity
import com.alireza.brochure.database.entity.SuperBannerEntity
import com.alireza.brochure.netwrok.model.BrochureDto
import com.alireza.brochure.netwrok.model.SuperBannerCarouselDto

fun BrochureDto.toBrochureEntity(index: Int, type: String): BaseBrochure.BrochureWrapperEntity = BaseBrochure.BrochureWrapperEntity(
    BrochureEntity(
        contentId = contentId.orEmpty(),
        title = title.orEmpty(),
        distance = distance?:0.0,
        imageUrl = brochureImage.orEmpty(),
        publishedFrom= publishedFrom.orEmpty(),
        publishedUntil = publishedUntil.orEmpty(),
        type = type,
        validFrom = validFrom.orEmpty(),
        validUntil = validUntil.orEmpty(),
        orderIndex = index,
        storeLocation = closestStore?.toStoreLocationEntity()?: StoreLocationEntity()
    )
)

fun SuperBannerCarouselDto.toSuperBannerEntity(index: Int, groupId: String): SuperBannerEntity =  SuperBannerEntity(
    id = id.orEmpty(),
    imageUrl = imageUrl.orEmpty(),
    publishedFrom= publishedFrom.orEmpty(),
    publishedUntil = publishedUntil.orEmpty(),
    orderIndex = index,
    clickUrl = clickUrl.orEmpty(),
    groupId = groupId
)