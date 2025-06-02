package com.alireza.brochure.data.repository

import com.alireza.brochure.data.fakeData.brochureList
import com.alireza.brochure.data.localCache.LocalDataStore
import com.alireza.brochure.data.remote.apiService.BrochureApiService
import com.alireza.brochure.data.remote.dto.BrochureListDto
import com.alireza.brochure.model.brochure.BrochureModel
import com.alireza.brochure.model.appError.AppError
import com.alireza.brochure.model.baseResult.BaseResult
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import retrofit2.Response
import java.io.IOException
import kotlin.test.Test

class BrochureRepositoryImplTest {


    private lateinit var brochureApiService: BrochureApiService
    private lateinit var localDataStore: LocalDataStore
    private lateinit var brochureRepository: BrochureRepositoryImpl

    @Before
    fun setUp() {
        brochureApiService = mockk()
        localDataStore = mockk()
        brochureRepository = BrochureRepositoryImpl(brochureApiService, localDataStore)
    }



    @Test
    fun `brochureList returns success with valid data`() = runTest {

        val response = Response.success(BrochureListDto())

        val expectedResult = brochureList

        coEvery { brochureApiService.getBrochureList() } returns response
        every { localDataStore.get() } returns expectedResult as List<BrochureModel>?
        every { localDataStore.set(any()) } returns Unit

        val result = brochureRepository.getBrochureList()

        assertTrue(result is BaseResult.Success)
        assertEquals(brochureList, (result as BaseResult.Success).data)
    }

    @Test
    fun `brochureList caches data from remote source`() = runTest {

        val response = Response.success(BrochureListDto())

        coEvery { brochureApiService.getBrochureList() } returns response
        every { localDataStore.set(any()) } returns Unit

        brochureRepository.getBrochureList()

        verify(exactly = 1) { localDataStore.set(emptyList()) }
    }

    @Test
    fun `brochureList returns failure when api call fails`() = runTest {

        val errorResponse = Response.error<BrochureListDto>(500, "Error".toResponseBody())
        coEvery { brochureApiService.getBrochureList() } returns errorResponse

        val result = brochureRepository.getBrochureList()

        assertTrue(result is BaseResult.Failure)
        assertTrue((result as BaseResult.Failure).error is AppError.ServerError)
    }

    @Test
    fun `brochureList returns cached data when api call throws IOException and cache is not empty`() = runTest {

        coEvery { brochureApiService.getBrochureList() } throws IOException()
        val cachedData = brochureList
        every { localDataStore.get() } returns cachedData as List<BrochureModel>?

        val result = brochureRepository.getBrochureList()

        assertTrue(result is BaseResult.Success)
        assertEquals(cachedData, (result as BaseResult.Success).data)
        assertTrue(result.fromCache)
    }


    @Test
    fun `cachedBrochureList returns filtered data from cache`() = runTest {

        val cachedData = brochureList
        val expectedResult = brochureList
        every { localDataStore.get() } returns cachedData as List<BrochureModel>?

        val result = brochureRepository.getCachedBrochureList()

        assertEquals(expectedResult, result)
    }

}