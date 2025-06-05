package com.alireza.brochure.database.dataSource

import com.alireza.brochure.database.entity.BrochureEntity
import com.alireza.brochure.database.entity.SuperBannerEntity


interface LocalDataSource {
    suspend fun getBrochureList(): List<BrochureEntity>

    suspend fun saveBrochureList(brochureList: List<BrochureEntity>)

    suspend fun saveBrochure(brochure: BrochureEntity)

    suspend fun deleteAllBrochure()

    suspend fun findBrochureById(brochureId: String): BrochureEntity

    suspend fun saveSuperBanner(banner: List<SuperBannerEntity>)

    suspend fun getSuperBanner(): List<SuperBannerEntity>
}