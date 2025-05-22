package com.alireza.brochure.data.di

import com.alireza.brochure.data.localCache.LocalDataStore
import com.alireza.brochure.data.localCache.LocalDataStoreImpl
import com.alireza.brochure.data.remote.apiService.BrochureApiService
import com.alireza.brochure.data.repository.BrochureRepositoryImpl
import com.alireza.brochure.domain.repository.BrochureRepository
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
        api: BrochureApiService,
        localDataStore: LocalDataStore
    ): BrochureRepository {
        return BrochureRepositoryImpl(api, localDataStore)
    }
}