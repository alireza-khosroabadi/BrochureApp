package com.alireza.brochure.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BrochureDto(
    @SerialName("brochureImage")
    val brochureImage: String? = "",
    @SerialName("closestStore")
    val closestStore: ClosestStoreDto? =null,
    @SerialName("contentBadges")
    val contentBadges: List<BrochureBadgeDto>? = listOf(),
    @SerialName("contentId")
    val contentId: String? = "",
    @SerialName("distance")
    val distance: Double? = 0.0,
    @SerialName("hideValidityDate")
    val hideValidityDate: Boolean? = false,
    @SerialName("id")
    val id: Long? = 0,
    @SerialName("pageCount")
    val pageCount: Int? = 0,
    @SerialName("publishedFrom")
    val publishedFrom: String? = "",
    @SerialName("publishedUntil")
    val publishedUntil: String? = "",
    @SerialName("publisher")
    val publisher: PublisherDto? = PublisherDto(),
    @SerialName("title")
    val title: String? = "",
    @SerialName("type")
    val type: String? = "",
    @SerialName("validFrom")
    val validFrom: String? = "",
    @SerialName("validUntil")
    val validUntil: String? = "",
)