package com.alireza.brochure.feature_brochure.brochure.ui.previewProvider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.alireza.brochure.domain.model.appError.AppError
import com.alireza.brochure.domain.model.brochure.PremiumBrochure
import com.alireza.brochure.domain.model.brochure.RegularBrochure
import com.alireza.brochure.feature_brochure.brochure.state.BrochureUiState

class BrochureScreenParameterProvider: PreviewParameterProvider<BrochureUiState> {
    override val values: Sequence<BrochureUiState>
        get() = sequenceOf(
            BrochureUiState.Loading,
            BrochureUiState.EmptyState,
            BrochureUiState.Error(AppError.Timeout),
            BrochureUiState.Success(brochures = listOf(
                RegularBrochure(id= "1", name = "Brochure", distance = 5.5, imageUrl = ""),
                PremiumBrochure(id= "2", name = "Premium Brochure", distance = 5.5, imageUrl = ""),
            ), fromCache = false),
        )

}