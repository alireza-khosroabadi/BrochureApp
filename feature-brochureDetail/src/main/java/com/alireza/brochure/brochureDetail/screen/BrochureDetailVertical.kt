package com.alireza.brochure.brochureDetail.screen

import BrochureDetailPreviewParameterProvider
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.alireza.brochure.designsystem.component.AsyncImageLoader
import com.alireza.brochure.model.brochureDetail.BrochureDetail

@Composable
fun BrochureDetailVertical(brochure: BrochureDetail) {
    val scrollState = rememberScrollState()
    Column {
        AsyncImageLoader(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            imageUrl = brochure.imageUrl
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .scrollable(scrollState, Orientation.Vertical)
        ) {
            ValidDateRow(brochure.validFrom, brochure.validUntil)
            StoreLocationBox(brochure.storeLocation)

        }
    }
}

@Preview
@Composable
fun BrochureDetailVerticalPreview(@PreviewParameter(BrochureDetailPreviewParameterProvider::class) brochure: BrochureDetail) {
    BrochureDetailVertical(brochure = brochure)
}