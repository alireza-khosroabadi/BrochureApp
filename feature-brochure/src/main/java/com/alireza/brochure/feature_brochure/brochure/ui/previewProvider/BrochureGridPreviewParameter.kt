package com.alireza.brochure.feature_brochure.brochure.ui.previewProvider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.alireza.brochure.domain.model.brochure.Brochure
import com.alireza.brochure.domain.model.brochure.PremiumBrochure
import com.alireza.brochure.domain.model.brochure.RegularBrochure

class BrochureGridPreviewParameter : PreviewParameterProvider<List<Brochure>> {
    override val values: Sequence<List<Brochure>>
        get() = sequenceOf(listOf(
            RegularBrochure(id = "1", name = "number 0", distance = 5.0, imageUrl = ""),
            PremiumBrochure(id = "2", name = "number 0", distance = 5.0, imageUrl = ""),
            RegularBrochure(id = "3", name = "number 0", distance = 5.0, imageUrl = "")
        ))
}