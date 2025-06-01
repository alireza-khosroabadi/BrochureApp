package com.alireza.brochure.netwrok.apiService

import com.alireza.brochure.netwrok.model.BrochureListDto
import retrofit2.Response
import retrofit2.http.GET

internal interface BrochureApiService {
    @GET("shelf.json")
    suspend fun getBrochureList(): Response<BrochureListDto>
}