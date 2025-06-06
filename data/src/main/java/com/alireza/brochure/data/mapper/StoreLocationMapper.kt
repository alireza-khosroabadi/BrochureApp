package com.alireza.brochure.data.mapper

import com.alireza.brochure.database.entity.StoreLocationEntity
import com.alireza.brochure.model.brochureDetail.StoreLocation
import com.alireza.brochure.netwrok.model.ClosestStoreDto

fun ClosestStoreDto.toStoreLocationEntity(): StoreLocationEntity = StoreLocationEntity(
    city = city.orEmpty(),
    latitude = latitude?:0.0,
    longitude = longitude?:0.0,
    street = street.orEmpty(),
    streetNumber = streetNumber.orEmpty(),
    zipCode = zip.orEmpty()
)

fun StoreLocationEntity.toStoreLocation(): StoreLocation = StoreLocation(
    city = city,
    latitude = latitude,
    longitude = latitude,
    street = street,
    streetNumber = streetNumber,
    zipCode = zipCode
)