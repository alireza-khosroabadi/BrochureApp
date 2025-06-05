package com.alireza.brochure.database.dataSource

import com.alireza.brochure.database.dao.BrochureDao
import com.alireza.brochure.database.dataBase.BrochureAppDataBase
import com.alireza.brochure.database.entity.BrochureEntity
import com.alireza.brochure.database.entity.SuperBannerEntity
import javax.inject.Inject

internal class RoomDataSource @Inject constructor(private val dataBase: BrochureAppDataBase) :
    LocalDataSource {
    override suspend fun getBrochureList(): List<BrochureEntity> =
        dataBase.brochureDao().getAllBrochure()

    override suspend fun saveBrochureList(brochureList: List<BrochureEntity>) =
        dataBase.brochureDao().insertAll(brochureList)

    override suspend fun saveBrochure(brochure: BrochureEntity) {
        dataBase.brochureDao().insert(brochure)
    }

    override suspend fun deleteAllBrochure() =
        dataBase.brochureDao().clearTable()

    override suspend fun findBrochureById(brochureId: String): BrochureEntity =
        dataBase.brochureDao().findBrochureById(brochureId)

    override suspend fun saveSuperBanner(banner: List<SuperBannerEntity>) {
        dataBase.superBannerDao().insertAll(banner)
    }

    override suspend fun getSuperBanner(): List<SuperBannerEntity> =
        dataBase.superBannerDao().getAll()

}