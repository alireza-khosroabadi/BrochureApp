package com.alireza.brochure.feature_brochure.brochure.state

import androidx.compose.runtime.Stable
import com.alireza.brochure.core.ui.errorScreen.ErrorUiModel
import com.alireza.brochure.domain.model.brochure.Brochure
import com.alireza.brochure.domain.model.appError.AppError

@Stable
sealed class BrochureUiState {
    data class Success(val brochures: List<Brochure>, val fromCache: Boolean) : BrochureUiState()
    data class Error(val error: ErrorUiModel) : BrochureUiState()
    object EmptyState : BrochureUiState()
    object Loading : BrochureUiState()
}
