package com.alireza.brochure.data.repository

import com.alireza.brochure.data.jsonParser.BrochureParserFactory
import com.alireza.brochure.data.localCache.LocalDataStore
import com.alireza.brochure.data.mapper.ErrorMapper
import com.alireza.brochure.data.remote.apiService.BrochureApiService
import com.alireza.brochure.data.remote.dto.ContentDto
import com.alireza.brochure.domain.model.brochure.Brochure
import com.alireza.brochure.domain.model.brochure.BrochureModel
import com.alireza.brochure.domain.repository.BrochureRepository
import com.alireza.brochureApp.common.model.appError.AppError
import com.alireza.brochureApp.common.model.baseResult.BaseResult
import java.io.IOException
import javax.inject.Inject

class BrochureRepositoryImpl @Inject constructor(
    private val brochureApiService: BrochureApiService,
    private val localDataStore: LocalDataStore
) : BrochureRepository {

    @Suppress("UNCHECKED_CAST")
    override suspend fun getBrochureList(): BaseResult<List<Brochure>> {
        return try {
            val response = brochureApiService.getBrochureList()
            if (!response.isSuccessful) {
                return BaseResult.Failure(
                    AppError.ServerError(response.errorBody()?.string())
                )
            }
            // Parse and store new data
            val parsed = processResponse(response.body()?.embedded?.contents)
            localDataStore.set(parsed)
            // Always read from cache and filter
            val result = localDataStore.get().orEmpty().filter(::isValidBrochure)
            BaseResult.Success(result)
        } catch (e: IOException) {
            // No internet — fallback to cached data
            val cached = localDataStore.get().orEmpty().filter(::isValidBrochure)
            if (cached.isNotEmpty()) {
                BaseResult.Success(cached, fromCache = true)
            } else {
                BaseResult.Failure(AppError.NoInternet)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            BaseResult.Failure(ErrorMapper.fromThrowable(e))
        } as BaseResult<List<Brochure>>
    }

    @Suppress("UNCHECKED_CAST")
    override suspend fun getCachedBrochureList(): List<Brochure> {
        return localDataStore.get().orEmpty().filter(::isValidBrochure) as List<Brochure>
    }

    private fun isValidBrochure(item: BrochureModel): Boolean {
        return item is Brochure
    }

    private fun processResponse(items: List<ContentDto>?): List<BrochureModel> {
        return items.orEmpty().mapNotNull { item ->
            val parser = BrochureParserFactory.getParser(item.contentType.orEmpty())
            item.content?.let { parser?.parse(it) }
        }
    }

}