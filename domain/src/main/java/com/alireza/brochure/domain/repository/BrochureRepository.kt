package com.alireza.brochure.domain.repository

import com.alireza.brochure.domain.model.baseResult.BaseResult
import com.alireza.brochure.domain.model.brochure.Brochure

interface BrochureRepository {
    suspend fun getBrochureList(): BaseResult<List<Brochure>>
    suspend fun getCachedBrochureList(): List<Brochure>
}