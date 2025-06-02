package com.alireza.brochure.domain.useCase

import com.alireza.brochure.model.brochure.Brochure
import com.alireza.brochure.domain.repository.BrochureRepository
import com.alireza.brochure.model.appError.AppError
import com.alireza.brochure.model.baseResult.BaseResult
import javax.inject.Inject
import kotlin.collections.filter

class FilterBrochureUseCase @Inject constructor(private val repository: BrochureRepository){

    suspend fun invoke(filterByDistance: Boolean, distance: Double = 5.0): BaseResult<List<Brochure>>{
        val cached = repository.getCachedBrochureList()
        if (isOfflineAndEmpty(cached)) {
            return BaseResult.Failure(AppError.NoInternet)
        }
        val filtered = if (filterByDistance) {
            cached.filter { it.distance <= distance }
        } else {
            cached
        }
        return BaseResult.Success(filtered)
    }
}

private fun isOfflineAndEmpty(cached: List<Brochure>) =
    cached.isEmpty()