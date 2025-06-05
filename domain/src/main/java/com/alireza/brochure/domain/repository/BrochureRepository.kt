package com.alireza.brochure.domain.repository

import com.alireza.brochure.model.brochure.Brochure
import com.alireza.brochure.model.baseResult.BaseResult
import com.alireza.brochure.model.brochure.BrochureModel

interface BrochureRepository {
    suspend fun getBrochureList(): BaseResult<List<BrochureModel>>
    suspend fun getCachedBrochureList(): List<Brochure>
}