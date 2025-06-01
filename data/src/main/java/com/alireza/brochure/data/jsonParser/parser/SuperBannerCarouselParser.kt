package com.alireza.brochure.data.jsonParser.parser

import com.alireza.brochure.data.jsonParser.JsonParser
import com.alireza.brochure.data.mapper.toSuperBannerCarousel
import com.alireza.brochure.domain.model.brochure.BrochureModel
import com.alireza.brochure.domain.model.brochure.SuperBannerCarousel
import com.alireza.brochure.netwrok.model.SuperBannerCarouselDto
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject


class SuperBannerCarouselParser: JsonParser<BrochureModel> {
        override fun parse(json: JsonElement): BrochureModel? {
            return try {
                val contentList : MutableList<SuperBannerCarouselDto>? = mutableListOf()
                json.jsonArray.map {
                    it.jsonObject["content"]?.let { it1 ->
                        val model =  Json.decodeFromJsonElement<SuperBannerCarouselDto>(it1)
                        contentList?.add(model)
                    }

                }
                contentList?.let {
                    SuperBannerCarousel(
                        banner =  it.map { it.toSuperBannerCarousel() }
                    )
                }

            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

}