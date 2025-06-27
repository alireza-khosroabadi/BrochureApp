package com.alireza.brochure.brochureDetail.screen.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.alireza.brochure.model.brochureDetail.BrochureDetail
import com.alireza.brochure.model.brochureDetail.StoreLocation
import storeLocation

class BrochureDetailScreenPreviewParameterProvider : PreviewParameterProvider<BrochureDetail?> {
    override val values = sequenceOf(
        BrochureDetail(
            id = "id1",
            title = "Test Brochure Detail",
            distance = 1.0,
            type = "brochure",
            imageUrl = "img.jpg",
            publishedFrom = "2023-01-01",
            publishedUntil = "2023-12-31",
            validFrom = "2023-01-01",
            validUntil = "2023-12-31",
            orderIndex = 1,
            storeLocation = storeLocation
        ),
        null
    )
} 