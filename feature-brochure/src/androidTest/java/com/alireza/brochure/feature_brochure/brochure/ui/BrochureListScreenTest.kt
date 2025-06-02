package com.alireza.brochure.feature_brochure.brochure.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.alireza.brochure.ui.component.errorScreen.ErrorUiModel
import com.alireza.brochure.model.brochure.RegularBrochure
import com.alireza.brochure.feature_brochure.brochure.state.BrochureUiState
import com.alireza.brochure.feature_brochure.brochure.viewModel.BrochureListViewModel
import com.alireza.brochure.model.appError.AppError
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test

class BrochureListScreenTest {


    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun brochureListScreen_displaysLoadingState() {
        val viewModel: BrochureListViewModel = mockk()
        val uiState = MutableStateFlow<BrochureUiState>(BrochureUiState.Loading)
        val filterState = MutableStateFlow(false)

        coEvery { viewModel.uiState } returns uiState
        coEvery { viewModel.isFilterActive } returns filterState

        composeTestRule.setContent {
            BrochureListScreen(viewModel = viewModel)
        }

        composeTestRule.onNodeWithTag("Loading").assertIsDisplayed()
    }

    @Test
    fun brochureListScreen_displaysGridState() {
        val viewModel: BrochureListViewModel = mockk()
        val data = listOf(RegularBrochure("1", "Brochure 1", 3.0, "image1.jpg"))
        val uiState = MutableStateFlow<BrochureUiState>(BrochureUiState.Success(data,false))
        val filterState = MutableStateFlow(false)

        coEvery { viewModel.uiState } returns uiState
        coEvery { viewModel.isFilterActive } returns filterState

        composeTestRule.setContent {
            BrochureListScreen(viewModel = viewModel)
        }

        composeTestRule.onNodeWithTag("BrochureGrid").assertIsDisplayed()
    }


    @Test
    fun brochureListScreen_displaysOfflineGridState() {
        val viewModel: BrochureListViewModel = mockk()
        val data = listOf(RegularBrochure("1", "Brochure 1", 3.0, "image1.jpg"))
        val uiState = MutableStateFlow<BrochureUiState>(BrochureUiState.Success(data,true))
        val filterState = MutableStateFlow(false)

        coEvery { viewModel.uiState } returns uiState
        coEvery { viewModel.isFilterActive } returns filterState

        composeTestRule.setContent {
            BrochureListScreen(viewModel = viewModel)
        }

        composeTestRule.onNodeWithTag("OfflineMode").assertIsDisplayed()
        composeTestRule.onNodeWithTag("BrochureGrid").assertIsDisplayed()
    }

    @Test
    fun brochureListScreen_displaysErrorState() {
        val viewModel: BrochureListViewModel = mockk()
        val uiState = MutableStateFlow<BrochureUiState>(BrochureUiState.Error(ErrorUiModel(AppError.Timeout.getErrorMessage())))
        val filterState = MutableStateFlow(false)

        coEvery { viewModel.uiState } returns uiState
        coEvery { viewModel.isFilterActive } returns filterState

        composeTestRule.setContent {
            BrochureListScreen(viewModel = viewModel)
        }

        composeTestRule.onNodeWithTag("ErrorScreen").assertIsDisplayed()
    }

    @Test
    fun brochureListScreen_displaysEmptyState() {
        val viewModel: BrochureListViewModel = mockk()
        val uiState = MutableStateFlow<BrochureUiState>(BrochureUiState.EmptyState)
        val filterState = MutableStateFlow(false)

        coEvery { viewModel.uiState } returns uiState
        coEvery { viewModel.isFilterActive } returns filterState

        composeTestRule.setContent {
            BrochureListScreen(viewModel = viewModel)
        }

        composeTestRule.onNodeWithTag("EmptyState").assertIsDisplayed()
    }

}