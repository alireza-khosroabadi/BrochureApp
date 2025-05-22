package com.alireza.brochure.domain.useCase

import com.alireza.brochure.domain.model.baseResult.BaseResult
import com.alireza.brochure.domain.model.brochure.Brochure
import com.alireza.brochure.domain.repository.BrochureRepository
import javax.inject.Inject

class BrochureListUseCase @Inject constructor(private val repository: BrochureRepository) {

    suspend operator fun invoke(): BaseResult<List<Brochure>> =
        repository.brochureList()

}