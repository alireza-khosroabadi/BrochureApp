package com.alireza.brochure.netwrok.di

import com.alireza.brochure.netwrok.NetworkDataSource
import com.alireza.brochure.netwrok.dataSource.NetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface NetworkDataSourceModule {
    
    @Binds
    fun bindNetworkDataSource(networkDataSourceImpl: NetworkDataSourceImpl): NetworkDataSource
}