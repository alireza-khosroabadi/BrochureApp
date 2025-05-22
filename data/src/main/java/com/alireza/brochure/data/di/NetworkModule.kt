package com.alireza.brochure.data.di

import com.alireza.brochure.data.remote.apiService.BrochureApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val TIMEOUT_VALUE_SECONDS = 10L

    @Provides
    fun provideBaseUrl() = "https://mobile-s3-test-assets.aws-sdlc-bonial.com/"

    @Provides
    fun createOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_VALUE_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_VALUE_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_VALUE_SECONDS, TimeUnit.SECONDS)
        .callTimeout(TIMEOUT_VALUE_SECONDS, TimeUnit.SECONDS)
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

    @Provides
    @Singleton
    fun provideShelfApi(retrofit: Retrofit): BrochureApiService =
        retrofit.create(BrochureApiService::class.java)

}