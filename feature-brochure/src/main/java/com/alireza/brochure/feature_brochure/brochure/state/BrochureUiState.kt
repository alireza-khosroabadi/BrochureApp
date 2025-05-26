package com.alireza.brochure.feature_brochure.brochure.state

import androidx.compose.runtime.Stable
import com.alireza.brochure.domain.model.brochure.Brochure
import com.alireza.brochureApp.common.model.appError.AppError

@Stable
sealed class BrochureUiState {
    data class Success(val brochures: List<Brochure>, val fromCache: Boolean) : BrochureUiState()
    data class Error(val error: AppError) : BrochureUiState()
    object EmptyState : BrochureUiState()
    object Loading : BrochureUiState()
}
