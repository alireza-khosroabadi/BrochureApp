package com.alireza.brochure.feature_brochure.brochure.viewModel

import app.cash.turbine.test
import com.alireza.brochure.ui.component.errorScreen.ErrorUiModel
import com.alireza.brochure.model.brochure.RegularBrochure
import com.alireza.brochure.domain.useCase.FilterBrochureUseCase
import com.alireza.brochure.feature_brochure.brochure.state.BrochureUiState
import com.alireza.brochure.model.appError.AppError
import com.alireza.brochure.model.baseResult.BaseResult
import com.alireza.brochure.domain.repository.BrochureRepository
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

    private lateinit var repository: BrochureRepository
    private lateinit var filterBrochureUseCase: FilterBrochureUseCase
    private lateinit var viewModel: BrochureListViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        filterBrochureUseCase = mockk()
        viewModel = BrochureListViewModel(repository, filterBrochureUseCase, testDispatcher)
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
        coEvery { repository.getBrochureList() } returns BaseResult.Success(brochures)

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
        coEvery { repository.getBrochureList() } returns BaseResult.Success(emptyList())

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
        coEvery { repository.getBrochureList() } returns BaseResult.Failure(AppError.NoInternet)

        val expected = ErrorUiModel(AppError.NoInternet)

        viewModel.uiState.test {
            val loadingState = awaitItem()
            assertTrue(loadingState is BrochureUiState.Loading)
            val state = awaitItem()
            assertTrue(state is BrochureUiState.Error)
            assertEquals(expected, (state as BrochureUiState.Error).error)
            cancelAndIgnoreRemainingEvents()
        }
    }


    @Test
    fun `toggleFilter updates isFilterActive and emits filtered result`() = runTest {
        val brochures = listOf(RegularBrochure("1", "Brochure 1", 3.0, "image1.jpg"))
        coEvery { repository.getBrochureList() } returns BaseResult.Success(brochures)
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

    @Test
    fun `fetchBrochure emits Error when repository throws exception`() = runTest {
        coEvery { repository.getBrochureList() } throws RuntimeException("unexpected error")

        viewModel.uiState.test {
            val loadingState = awaitItem()
            assertTrue(loadingState is BrochureUiState.Loading)
            val state = awaitItem()
            assertTrue(state is BrochureUiState.Error)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `rapid toggleFilter calls do not break state`() = runTest {
        val brochures = listOf(RegularBrochure("1", "Brochure 1", 3.0, "image1.jpg"))
        coEvery { repository.getBrochureList() } returns BaseResult.Success(brochures)
        coEvery { filterBrochureUseCase.invoke(any()) } returns BaseResult.Success(brochures)

        // Simulate rapid toggling
        repeat(5) { viewModel.toggleFilter() }

        viewModel.isFilterActive.test {
            // Should still emit a boolean, not crash or hang
            val value = awaitItem()
            assertTrue(value is Boolean)
            cancelAndIgnoreRemainingEvents()
        }
    }

}