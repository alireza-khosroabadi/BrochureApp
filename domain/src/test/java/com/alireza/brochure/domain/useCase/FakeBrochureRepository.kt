package com.alireza.brochure.domain.useCase

import com.alireza.brochure.model.brochure.Brochure
import com.alireza.brochure.model.brochure.PremiumBrochure
import com.alireza.brochure.model.brochure.RegularBrochure
import com.alireza.brochure.domain.repository.BrochureRepository
import com.alireza.brochure.model.baseResult.BaseResult
import com.alireza.brochure.model.brochure.BrochureModel
import com.alireza.brochure.model.brochureDetail.BrochureDetail

class FakeBrochureRepository: BrochureRepository {
    override suspend fun getBrochureList(): BaseResult<List<BrochureModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCachedBrochureList(): List<Brochure> = listOf(
            RegularBrochure("1", "Brochure 1", 3.0, "image1.jpg"),
            PremiumBrochure("2", "Brochure 2", 7.0, "image2.jpg"),
            RegularBrochure("3", "Brochure 3", 5.0, "image3.jpg")
        )

    override suspend fun findBrochureById(brochureId: String): BrochureDetail {
        TODO("Not yet implemented")
    }

}