package com.alireza.brochure.data.di

import com.alireza.brochure.data.localCache.LocalDataStore
import com.alireza.brochure.data.localCache.LocalDataStoreImpl
import com.alireza.brochure.data.repository.BrochureRepositoryImpl
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
    fun provideLocalDataStore(): LocalDataStore = LocalDataStoreImpl()

    @Provides
    @Singleton
    fun provideBrochureRepository(
        networkDataSource: NetworkDataSource,
        localDataStore: LocalDataStore
    ): BrochureRepository {
        return BrochureRepositoryImpl(networkDataSource, localDataStore)
    }
}