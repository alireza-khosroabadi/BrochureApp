package com.alireza.brochure.feature_brochure.brochure.ui.previewProvider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.alireza.brochure.ui.component.errorScreen.ErrorUiModel
import com.alireza.brochure.model.brochure.PremiumBrochure
import com.alireza.brochure.model.brochure.RegularBrochure
import com.alireza.brochure.feature_brochure.brochure.state.BrochureUiState
import com.alireza.brochure.model.appError.AppError

class BrochureScreenParameterProvider: PreviewParameterProvider<BrochureUiState> {
    override val values: Sequence<BrochureUiState>
        get() = sequenceOf(
            BrochureUiState.Loading,
            BrochureUiState.EmptyState,
            BrochureUiState.Error(ErrorUiModel(AppError.Timeout)),
            BrochureUiState.Success(brochures = listOf(
                RegularBrochure(id= "1", name = "Brochure", distance = 5.5, imageUrl = ""),
                PremiumBrochure(id= "2", name = "Premium Brochure", distance = 5.5, imageUrl = ""),
            ), fromCache = false),
        )

}