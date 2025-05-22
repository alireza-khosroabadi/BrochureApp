package com.alireza.brochure.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BrochureListDto(
    @SerialName("_embedded")
    val embedded: EmbeddedDto? = null,
    @SerialName("_links")
    val links: LinksDto? = null,
    @SerialName("page")
    val page: PageDto? = null
)