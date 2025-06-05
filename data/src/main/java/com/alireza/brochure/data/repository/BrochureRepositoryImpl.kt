package com.alireza.brochure.data.repository

import com.alireza.brochure.data.jsonParser.BrochureParserFactory
import com.alireza.brochure.data.mapper.ErrorMapper
import com.alireza.brochure.data.mapper.toBrochureModel
import com.alireza.brochure.database.dataSource.LocalDataSource
import com.alireza.brochure.database.entity.BaseBrochure
import com.alireza.brochure.model.appError.AppError
import com.alireza.brochure.model.baseResult.BaseResult
import com.alireza.brochure.model.brochure.Brochure
import com.alireza.brochure.model.brochure.BrochureModel
import com.alireza.brochure.domain.repository.BrochureRepository
import com.alireza.brochure.netwrok.NetworkDataSource
import com.alireza.brochure.netwrok.model.ContentDto
import java.io.IOException
import javax.inject.Inject

class BrochureRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val dataSource: LocalDataSource
) : BrochureRepository {

    @Suppress("UNCHECKED_CAST")
    override suspend fun getBrochureList(): BaseResult<List<BrochureModel>> {
        return try {
            val response = networkDataSource.getBrochureList()
            if (!response.isSuccessful) {
                return BaseResult.Failure(
                    AppError.ServerError(response.errorBody()?.string())
                )
            }
            // Parse and store new data
            processResponse(response.body()?.embedded?.contents)
            // Always read from cache and filter
            BaseResult.Success(getCachedData().map { it.toBrochureModel() })
        } catch (e: IOException) {
            // No internet — fallback to cached data
            val cached = getCachedData()
            if (cached.isNotEmpty()) {
                BaseResult.Success(cached.map { it.toBrochureModel() }, fromCache = true)
            } else {
                BaseResult.Failure(AppError.NoInternet)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            BaseResult.Failure(ErrorMapper.fromThrowable(e))
        }
    }

    private suspend fun getCachedData(): MutableList<BaseBrochure> {
        val banners = dataSource.getSuperBanner().groupBy { it.orderIndex }
        val brochures = dataSource.getBrochureList().associateBy { it.orderIndex }

        val maxIndex = (banners.keys + brochures.keys).maxOrNull() ?: 0
        val result = mutableListOf<BaseBrochure>()

        for (i in 0..maxIndex) {
            when {
                banners.containsKey(i) -> {
                    result.add(BaseBrochure.SuperBannerWrapperEntity(banners[i]!!))
                }

                brochures.containsKey(i) -> {
                    result.add(BaseBrochure.BrochureWrapperEntity(brochures[i]!!))
                }
            }
        }
        return result
    }

    @Suppress("UNCHECKED_CAST")
    override suspend fun getCachedBrochureList(): List<Brochure> {
        return dataSource.getBrochureList() as List<Brochure>
    }

    private fun isValidBrochure(item: BrochureModel): Boolean {
        return item is Brochure
    }

    private suspend fun processResponse(items: List<ContentDto>?) {
        items.orEmpty().forEachIndexed { index, item ->
            val parser = BrochureParserFactory.getParser(index, item.contentType.orEmpty())
            item.content?.let {
                val parsed = parser?.parse(it)
                if (parsed is BaseBrochure.SuperBannerWrapperEntity) {
                    dataSource.saveSuperBanner(parsed.entity)
                } else if (parsed is BaseBrochure.BrochureWrapperEntity) {
                    dataSource.saveBrochure(parsed.entity)
                }
            }
        }
    }

}