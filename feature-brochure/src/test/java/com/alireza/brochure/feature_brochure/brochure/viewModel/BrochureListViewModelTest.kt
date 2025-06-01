package com.alireza.brochure.feature_brochure.brochure.viewModel

import app.cash.turbine.test
import com.alireza.brochure.domain.model.brochure.RegularBrochure
import com.alireza.brochure.domain.useCase.FilterBrochureUseCase
import com.alireza.brochure.domain.useCase.GetBrochureListUseCase
import com.alireza.brochure.feature_brochure.brochure.state.BrochureUiState
import com.alireza.brochure.domain.model.appError.AppError
import com.alireza.brochure.domain.model.baseResult.BaseResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BrochureListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var getBrochureListUseCase: GetBrochureListUseCase
    private lateinit var filterBrochureUseCase: FilterBrochureUseCase
    private lateinit var viewModel: BrochureListViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getBrochureListUseCase = mockk()
        filterBrochureUseCase = mockk()
        viewModel = BrochureListViewModel(getBrochureListUseCase, filterBrochureUseCase, testDispatcher)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `fetchBrochure first sets loading state`() = runTest {
        viewModel.uiState.test {
            assert(awaitItem() is BrochureUiState.Loading)
        }

    }

    @Test
    fun `fetchBrochure emits Success when data is available`() = runTest {
        val brochures = listOf(RegularBrochure("1", "Brochure 1", 3.0, "image1.jpg"))
        coEvery { getBrochureListUseCase() } returns BaseResult.Success(brochures)

        viewModel.uiState.test {
            val loadingState = awaitItem()
            assertTrue(loadingState is BrochureUiState.Loading)
            val state = awaitItem()
            assertEquals(brochures, (state as BrochureUiState.Success).brochures)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `fetchBrochure emits EmptyState when data is empty`() = runTest {
        coEvery { getBrochureListUseCase() } returns BaseResult.Success(emptyList())

        viewModel.uiState.test {
            val loadingState = awaitItem()
            assertTrue(loadingState is BrochureUiState.Loading)
            val state = awaitItem()
            assertTrue(state is BrochureUiState.EmptyState)
            cancelAndIgnoreRemainingEvents()
        }
    }



    @Test
    fun `fetchBrochure emits Error on failure`() = runTest {
        coEvery { getBrochureListUseCase() } returns BaseResult.Failure(AppError.NoInternet)

        viewModel.uiState.test {
            val loadingState = awaitItem()
            assertTrue(loadingState is BrochureUiState.Loading)
            val state = awaitItem()
            assertTrue(state is BrochureUiState.Error)
            assertEquals(AppError.NoInternet, (state as BrochureUiState.Error).error)
            cancelAndIgnoreRemainingEvents()
        }
    }


    @Test
    fun `toggleFilter updates isFilterActive and emits filtered result`() = runTest {
        val brochures = listOf(RegularBrochure("1", "Brochure 1", 3.0, "image1.jpg"))
        coEvery { getBrochureListUseCase() } returns BaseResult.Success(brochures)
        coEvery { filterBrochureUseCase.invoke(true) } returns BaseResult.Success(brochures)


        viewModel.toggleFilter()

        viewModel.uiState.test {
            val loadingState = awaitItem()
            assertTrue(loadingState is BrochureUiState.Loading)
            val state = awaitItem()
            assertTrue(state is BrochureUiState.Success)
            assertEquals(brochures, (state as BrochureUiState.Success).brochures)
            cancelAndIgnoreRemainingEvents()
        }

        viewModel.isFilterActive.test {
            assertEquals(false, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

}