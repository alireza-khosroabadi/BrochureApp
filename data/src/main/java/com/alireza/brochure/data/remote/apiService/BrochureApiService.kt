package com.alireza.brochure.data.remote.apiService

import com.alireza.brochure.data.remote.dto.BrochureListDto
import retrofit2.Response
import retrofit2.http.GET

interface BrochureApiService {
    @GET("shelf.json")
    suspend fun getBrochureList(): Response<BrochureListDto>

}