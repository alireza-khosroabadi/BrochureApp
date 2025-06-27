package com.alireza.brochure.database.di

import android.content.Context
import com.alireza.brochure.database.dao.BrochureDao
import com.alireza.brochure.database.dao.SuperBannerDao
import com.alireza.brochure.database.dataBase.BrochureAppDataBase
import com.alireza.brochure.database.dataSource.LocalDataSource
import com.alireza.brochure.database.dataSource.RoomDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    internal fun provideDatabase(
        @ApplicationContext applicationContext: Context
    ): BrochureAppDataBase = BrochureAppDataBase.getDataBase(applicationContext)

    @Provides
    internal fun providesBrochureDao(dataBase: BrochureAppDataBase): BrochureDao =
        dataBase.brochureDao()

    @Provides
    internal fun providesSuperBannerDao(dataBase: BrochureAppDataBase): SuperBannerDao =
        dataBase.superBannerDao()

    @Provides
    internal fun provideDataSource(dataBase: BrochureAppDataBase): LocalDataSource =
        RoomDataSource(dataBase)

}