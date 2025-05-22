package com.alireza.brochure.data.jsonParser

import com.alireza.brochure.data.jsonParser.parser.BrochureParser
import com.alireza.brochure.data.jsonParser.parser.BrochurePremiumParser
import com.alireza.brochure.data.jsonParser.parser.SuperBannerCarouselParser
import com.alireza.brochure.domain.model.brochure.Brochure
import com.alireza.brochure.domain.model.brochure.BrochureModel

object BrochureParserFactory {
    fun getParser(type: String): JsonParser<BrochureModel>? {
        return when (type) {
            "brochure" -> BrochureParser()
            "brochurePremium" -> BrochurePremiumParser()
            "superBannerCarousel" -> SuperBannerCarouselParser()
            else -> null
        }
    }
}