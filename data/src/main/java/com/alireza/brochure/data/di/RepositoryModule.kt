package com.alireza.brochure.data.di

import com.alireza.brochure.data.repository.BrochureRepositoryImpl
import com.alireza.brochure.database.dataSource.LocalDataSource
import com.alireza.brochure.domain.repository.BrochureRepository
import com.alireza.brochure.netwrok.NetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideBrochureRepository(
        networkDataSource: NetworkDataSource,
        dataStore: LocalDataSource
    ): BrochureRepository {
        return BrochureRepositoryImpl(networkDataSource, dataStore)
    }
}