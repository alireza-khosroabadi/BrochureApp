package com.alireza.brochure.brochure.brochure.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.alireza.brochure.feature_brochure.brochure.state.BrochureUiState
import com.alireza.brochure.feature_brochure.brochure.ui.BrochureListScreenContentPreview
import com.alireza.brochure.model.appError.AppError
import com.alireza.brochure.model.brochure.RegularBrochure
import com.alireza.brochure.ui.component.errorScreen.ErrorUiModel
import org.junit.Rule
import org.junit.Test

class BrochureListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showsLoadingIndicator_whenLoading() {
        composeTestRule.setContent {
            BrochureListScreenContentPreview(
                BrochureUiState.Loading
            )
        }
        composeTestRule.onNodeWithTag("Loading").assertExists()
    }

    @Test
    fun showsBrochureList_whenSuccess() {
        val brochures = listOf(RegularBrochure("1", "Brochure 1", 3.0, "image1.jpg"))
        composeTestRule.setContent {
            BrochureListScreenContentPreview(
                BrochureUiState.Success(brochures, fromCache = false)
            )
        }
        composeTestRule.onNodeWithTag("BrochureGrid").assertIsDisplayed()
        composeTestRule.onNodeWithText("Brochure 1").assertExists()
    }

    @Test
    fun showsOfflineAlert_whenOffline() {
        val brochures = listOf(RegularBrochure("1", "Brochure 1", 3.0, "image1.jpg"))

        composeTestRule.setContent {
            BrochureListScreenContentPreview(
                BrochureUiState.Success(brochures, fromCache = true)
            )
        }

        composeTestRule.onNodeWithTag("OfflineMode").assertIsDisplayed()
        composeTestRule.onNodeWithTag("BrochureGrid").assertIsDisplayed()
    }

    @Test
    fun showsEmptyState_whenEmpty() {
        composeTestRule.setContent {
            BrochureListScreenContentPreview(
                BrochureUiState.EmptyState
            )
        }
        composeTestRule.onNodeWithTag("EmptyState").assertExists()
    }

    @Test
    fun showsError_whenError() {
        composeTestRule.setContent {
            BrochureListScreenContentPreview(
                BrochureUiState.Error(
                    ErrorUiModel(
                        AppError.NoInternet
                    )
                )
            )
        }
        composeTestRule.onNodeWithText(ErrorUiModel(AppError.NoInternet).getErrorMessage())
            .assertExists()
    }
}