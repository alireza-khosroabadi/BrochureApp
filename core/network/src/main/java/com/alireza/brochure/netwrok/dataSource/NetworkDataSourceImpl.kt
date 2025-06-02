package com.alireza.brochure.netwrok.dataSource

import com.alireza.brochure.netwrok.NetworkDataSource
import com.alireza.brochure.netwrok.apiService.BrochureApiService
import com.alireza.brochure.netwrok.model.BrochureListDto
import retrofit2.Response
import javax.inject.Inject

internal class NetworkDataSourceImpl @Inject constructor(private val apiService: BrochureApiService) : NetworkDataSource {
    override suspend fun getBrochureList(): Response<BrochureListDto> = apiService.getBrochureList()
}