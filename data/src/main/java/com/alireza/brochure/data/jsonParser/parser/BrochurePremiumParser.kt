package com.alireza.brochure.data.jsonParser.parser

import com.alireza.brochure.data.jsonParser.JsonParser
import com.alireza.brochure.data.mapper.toBrochureEntity
import com.alireza.brochure.database.entity.BaseBrochure
import com.alireza.brochure.netwrok.model.BrochureDto
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement


class BrochurePremiumParser(private val index: Int, private val type: String): JsonParser<BaseBrochure> {
        override fun parse(json: JsonElement): BaseBrochure? {
            return try {
                val dataModel = Json.decodeFromJsonElement<BrochureDto>(json)
                dataModel.toBrochureEntity(index, type)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
}