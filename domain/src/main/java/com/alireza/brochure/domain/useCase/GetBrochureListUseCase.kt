package com.alireza.brochure.domain.useCase

import com.alireza.brochure.model.brochure.Brochure
import com.alireza.brochure.domain.repository.BrochureRepository
import com.alireza.brochure.model.baseResult.BaseResult
import com.alireza.brochure.model.brochure.BrochureModel
import javax.inject.Inject

class GetBrochureListUseCase @Inject constructor(private val repository: BrochureRepository) {

    suspend operator fun invoke(): BaseResult<List<BrochureModel>> =
        repository.getBrochureList()

}