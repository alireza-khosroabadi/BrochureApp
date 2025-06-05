package com.alireza.brochure.feature_brochure.brochure.state

import androidx.compose.runtime.Stable
import com.alireza.brochure.ui.component.errorScreen.ErrorUiModel
import com.alireza.brochure.model.brochure.Brochure
import com.alireza.brochure.model.brochure.BrochureModel

@Stable
sealed class BrochureUiState {
    data class Success(val brochures: List<BrochureModel>, val fromCache: Boolean) : BrochureUiState()
    data class Error(val error: ErrorUiModel) : BrochureUiState()
    object EmptyState : BrochureUiState()
    object Loading : BrochureUiState()
}
