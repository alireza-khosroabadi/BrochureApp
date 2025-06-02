package com.alireza.brochure.data.jsonParser.parser

import com.alireza.brochure.data.jsonParser.JsonParser
import com.alireza.brochure.data.mapper.toBrochurePremiumDomainModel
import com.alireza.brochure.model.brochure.BrochureModel
import com.alireza.brochure.netwrok.model.BrochureDto
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement


class BrochurePremiumParser: JsonParser<BrochureModel> {
        override fun parse(json: JsonElement): BrochureModel? {
            return try {
                val dataModel = Json.decodeFromJsonElement<BrochureDto>(json)
                dataModel.toBrochurePremiumDomainModel()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
}