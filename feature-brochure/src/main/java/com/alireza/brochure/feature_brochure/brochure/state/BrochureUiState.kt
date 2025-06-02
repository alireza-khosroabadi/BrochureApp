package com.alireza.brochure.feature_brochure.brochure.state

import androidx.compose.runtime.Stable
import com.alireza.brochure.ui.component.errorScreen.ErrorUiModel
import com.alireza.brochure.model.brochure.Brochure

@Stable
sealed class BrochureUiState {
    data class Success(val brochures: List<Brochure>, val fromCache: Boolean) : BrochureUiState()
    data class Error(val error: ErrorUiModel) : BrochureUiState()
    object EmptyState : BrochureUiState()
    object Loading : BrochureUiState()
}
