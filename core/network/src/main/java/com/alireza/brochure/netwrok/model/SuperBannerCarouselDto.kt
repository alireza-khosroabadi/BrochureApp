package com.alireza.brochure.netwrok.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SuperBannerCarouselDto(
    @SerialName("id"             ) var id             : String?    = null,
    @SerialName("publisher"      ) var publisher      : PublisherDto? = PublisherDto(),
    @SerialName("publishedFrom"  ) var publishedFrom  : String?    = null,
    @SerialName("publishedUntil" ) var publishedUntil : String?    = null,
    @SerialName("clickUrl"       ) var clickUrl       : String?    = null,
    @SerialName("imageUrl"       ) var imageUrl       : String?    = null
)
