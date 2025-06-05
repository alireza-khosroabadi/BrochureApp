package com.alireza.brochure.data.jsonParser

import com.alireza.brochure.data.jsonParser.parser.BrochureParser
import com.alireza.brochure.data.jsonParser.parser.BrochurePremiumParser
import com.alireza.brochure.data.jsonParser.parser.SuperBannerCarouselParser
import com.alireza.brochure.database.entity.BaseBrochure

object BrochureParserFactory {
    fun getParser(index: Int, type: String): JsonParser<BaseBrochure>? {
        return when (type) {
            "brochure" -> BrochureParser(index, type)
            "brochurePremium" -> BrochurePremiumParser(index, type)
            "superBannerCarousel" -> SuperBannerCarouselParser(index, type)
            else -> null
        }
    }
}