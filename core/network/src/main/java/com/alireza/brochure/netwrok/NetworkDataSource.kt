package com.alireza.brochure.netwrok

import com.alireza.brochure.netwrok.model.BrochureListDto
import retrofit2.Response

interface NetworkDataSource {
    suspend fun getBrochureList(): Response<BrochureListDto>
}