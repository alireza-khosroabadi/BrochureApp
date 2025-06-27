package com.alireza.brochure.data.jsonParser.parser

import com.alireza.brochure.data.jsonParser.JsonParser
import com.alireza.brochure.data.mapper.toSuperBannerEntity
import com.alireza.brochure.database.entity.BaseBrochure
import com.alireza.brochure.netwrok.model.SuperBannerCarouselDto
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject


class SuperBannerCarouselParser(private val index: Int, private val type: String) :
    JsonParser<BaseBrochure> {
    override fun parse(json: JsonElement): BaseBrochure? {
        return try {
            val contentList: MutableList<SuperBannerCarouselDto>? = mutableListOf()
            json.jsonArray.map {
                it.jsonObject["content"]?.let { it1 ->
                    val model = Json.decodeFromJsonElement<SuperBannerCarouselDto>(it1)
                    contentList?.add(model)
                }

            }
            contentList?.let {
                BaseBrochure.SuperBannerWrapperEntity(
                    entity = it.map { it.toSuperBannerEntity(index, groupId = type) }
                )
            }

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}