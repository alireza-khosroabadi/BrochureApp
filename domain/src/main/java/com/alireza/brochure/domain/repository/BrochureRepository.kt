package com.alireza.brochure.domain.repository

import com.alireza.brochure.domain.model.brochure.Brochure
import com.alireza.brochureApp.common.model.baseResult.BaseResult

interface BrochureRepository {
    suspend fun getBrochureList(): BaseResult<List<Brochure>>
    suspend fun getCachedBrochureList(): List<Brochure>
}