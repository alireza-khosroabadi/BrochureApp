package com.alireza.brochure.data.repository

import com.alireza.brochure.database.dataSource.LocalDataSource
import com.alireza.brochure.database.entity.BrochureEntity
import com.alireza.brochure.model.appError.AppError
import com.alireza.brochure.model.baseResult.BaseResult
import com.alireza.brochure.model.brochureDetail.BrochureDetail
import com.alireza.brochure.netwrok.NetworkDataSource
import com.alireza.brochure.netwrok.model.BrochureListDto
import com.alireza.brochure.netwrok.model.ContentDto
import com.alireza.brochure.netwrok.model.EmbeddedDto
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.io.IOException

class BrochureRepositoryImplTest {

    private val networkDataSource: NetworkDataSource = mockk()
    private val localDataSource: LocalDataSource = mockk()
    private lateinit var repository: BrochureRepositoryImpl

    @Before
    fun setUp() {
        repository = BrochureRepositoryImpl(networkDataSource, localDataSource)
    }

    @Test
    fun `getBrochureList returns success and caches data`() = runBlocking {
        val contentDto = ContentDto()
        val embeddedDto = EmbeddedDto(contents = listOf(contentDto))
        val body = BrochureListDto(embedded = embeddedDto)
        val response = Response.success(body)
        coEvery { networkDataSource.getBrochureList() } returns response
        coEvery { localDataSource.getSuperBanner() } returns emptyList()
        coEvery { localDataSource.getBrochureList() } returns emptyList()
        coEvery { localDataSource.saveSuperBanner(any()) } just Runs
        coEvery { localDataSource.saveBrochure(any()) } just Runs

        val result = repository.getBrochureList()
        assertTrue(result is BaseResult.Success)
    }

    @Test
    fun `getBrochureList returns failure on server error`() = runBlocking {
        val errorBody = ResponseBody.create(null, "Server error")
        val response = Response.error<BrochureListDto>(500, errorBody)
        coEvery { networkDataSource.getBrochureList() } returns response

        val result = repository.getBrochureList()
        assertTrue(result is BaseResult.Failure)
        assertTrue((result as BaseResult.Failure).error is AppError.ServerError)
    }

    @Test
    fun `getBrochureList returns cached data on IOException if cache is not empty`() = runBlocking {
        coEvery { networkDataSource.getBrochureList() } throws IOException()
        val cachedBrochure = BrochureEntity(
            contentId = "1",
            title = "Test",
            distance = 1.0,
            type = "Type",
            imageUrl = "url",
            publishedFrom = "2023-01-01",
            publishedUntil = "2023-12-31",
            validFrom = "2023-01-01",
            validUntil = "2023-12-31",
            orderIndex = 1,
            storeLocation = mockk(relaxed = true)
        )
        coEvery { localDataSource.getSuperBanner() } returns emptyList()
        coEvery { localDataSource.getBrochureList() } returns listOf(cachedBrochure)

        val result = repository.getBrochureList()
        assertTrue(result is BaseResult.Success)
        assertTrue((result as BaseResult.Success).fromCache)
    }

    @Test
    fun `getBrochureList returns failure on IOException if cache is empty`() = runBlocking {
        coEvery { networkDataSource.getBrochureList() } throws IOException()
        coEvery { localDataSource.getSuperBanner() } returns emptyList()
        coEvery { localDataSource.getBrochureList() } returns emptyList()

        val result = repository.getBrochureList()
        assertTrue(result is BaseResult.Failure)
        assertEquals(AppError.NoInternet, (result as BaseResult.Failure).error)
    }

    @Test
    fun `getBrochureList returns failure on generic exception`() = runBlocking {
        coEvery { networkDataSource.getBrochureList() } throws RuntimeException("Unknown error")
        coEvery { localDataSource.getSuperBanner() } returns emptyList()
        coEvery { localDataSource.getBrochureList() } returns emptyList()

        val result = repository.getBrochureList()
        assertTrue(result is BaseResult.Failure)
    }

    @Test
    fun `getCachedBrochureList returns data from localDataSource`() = runBlocking {
        val brochure = BrochureEntity(
            contentId = "1",
            title = "Test",
            distance = 1.0,
            type = "Type",
            imageUrl = "url",
            publishedFrom = "2023-01-01",
            publishedUntil = "2023-12-31",
            validFrom = "2023-01-01",
            validUntil = "2023-12-31",
            orderIndex = 1,
            storeLocation = mockk(relaxed = true)
        )
        coEvery { localDataSource.getBrochureList() } returns listOf(brochure)
        val result = repository.getCachedBrochureList()
        assertEquals(1, result.size)
    }

    @Test
    fun `findBrochureById returns mapped detail`() = runBlocking {
        val brochure = BrochureEntity(
            contentId = "1",
            title = "Test",
            distance = 1.0,
            type = "Type",
            imageUrl = "url",
            publishedFrom = "2023-01-01",
            publishedUntil = "2023-12-31",
            validFrom = "2023-01-01",
            validUntil = "2023-12-31",
            orderIndex = 1,
            storeLocation = mockk(relaxed = true)
        )
        coEvery { localDataSource.findBrochureById("1") } returns brochure
        val result = repository.findBrochureById("1")
        assertTrue(result is BrochureDetail)
    }
}