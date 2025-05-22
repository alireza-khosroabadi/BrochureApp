package com.alireza.brochure.domain.useCase

import com.alireza.brochure.domain.model.baseResult.BaseResult
import com.alireza.brochure.domain.model.brochure.Brochure
import com.alireza.brochure.domain.model.brochure.PremiumBrochure
import com.alireza.brochure.domain.model.brochure.RegularBrochure
import com.alireza.brochure.domain.repository.BrochureRepository

class FakeBrochureRepository: BrochureRepository {
    override suspend fun brochureList(): BaseResult<List<Brochure>> {
        TODO("Not yet implemented")
    }

    override suspend fun cachedBrochureList(): List<Brochure> = listOf(
            RegularBrochure("1", "Brochure 1", 3.0, "image1.jpg"),
            PremiumBrochure("2", "Brochure 2", 7.0, "image2.jpg"),
            RegularBrochure("3", "Brochure 3", 5.0, "image3.jpg")
        )

}