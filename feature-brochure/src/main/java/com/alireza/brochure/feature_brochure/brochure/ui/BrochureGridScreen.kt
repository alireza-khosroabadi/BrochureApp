@file:OptIn(ExperimentalMaterial3Api::class)

package com.alireza.brochure.feature_brochure.brochure.ui

import android.content.res.Configuration
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.alireza.brochure.designsystem.component.AsyncImageLoader
import com.alireza.brochure.designsystem.theme.BrochureAppTheme
import com.alireza.brochure.model.brochure.Brochure
import com.alireza.brochure.model.brochure.PremiumBrochure
import com.alireza.brochure.model.brochure.RegularBrochure
import com.alireza.brochure.feature_brochure.brochure.ui.previewProvider.BrochureGridPreviewParameter
import com.alireza.brochure.model.brochure.BrochureModel
import com.alireza.brochure.model.brochure.SuperBannerCarousel
import com.alireza.brochure.model.brochure.SuperBannerContent
import com.alireza.brochure.ui.component.AutoScrollingLazyRow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun BrochureGrid(brochures: List<BrochureModel>) {
    val configuration = LocalConfiguration.current
    val columns = remember(configuration) {
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 3 else 2
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {



        items(
            items = brochures,
            key = { item -> item.hashCode() },
            span = { item ->
                val span = if (item is RegularBrochure) 1 else columns
                GridItemSpan(span)
            },
            contentType = { it }
        ) { item ->
            when (item) {
                is RegularBrochure -> BrochureItem(regularBrochure = item)
                is PremiumBrochure -> BrochurePremiumItem(brochure = item)
                is SuperBannerCarousel -> BannerCarousel(item.banner)
            }
        }

    }
}


@Composable
fun BrochurePremiumItem(brochure: PremiumBrochure) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            AsyncImageLoader(
                imageUrl = brochure.imageUrl,
                contentDescription = "${brochure.name} brochure image",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3f / 1f)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = brochure.name,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun BrochureItem(regularBrochure: RegularBrochure) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            AsyncImageLoader(
                imageUrl = regularBrochure.imageUrl,
                contentDescription = "${regularBrochure.name} brochure image",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f / 1f)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = regularBrochure.name,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}


@Composable
fun BannerCarousel(items: List<SuperBannerContent>) {

//    AutoScrollingLazyRow(modifier = Modifier.fillMaxWidth(), list = items){ item ->
//        Card(
//            shape = MaterialTheme.shapes.small,
//            modifier = Modifier
//                .fillMaxWidth()
////                .aspectRatio(1f)
//                .padding(4.dp),
//            elevation = CardDefaults.cardElevation(4.dp)
//        ) {
//            AsyncImageLoader(
//                modifier = Modifier.fillMaxWidth().height(120.dp),
//                imageUrl = item.imageUrl
//            )
//        }
//    }
    HorizontalMultiBrowseCarousel(
        state = rememberCarouselState { items.count() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp),
        preferredItemWidth = LocalConfiguration.current.screenWidthDp.dp,
        itemSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) { i ->
        val item = items[i]
        AsyncImageLoader(modifier = Modifier.height(120.dp),
            imageUrl = item.imageUrl)
    }
}

@Preview
@Composable
private fun BrochureItemPreview() {
    BrochureAppTheme {
        BrochureItem(
            regularBrochure = RegularBrochure(
                id = "1",
                name = "number 0",
                distance = 5.0,
                imageUrl = ""
            )
        )
    }
}

@Preview
@Composable
private fun BrochurePremiumItemPreview() {
    BrochureAppTheme {
        BrochurePremiumItem(
            brochure = PremiumBrochure(
                id = "2",
                name = "number 0",
                distance = 5.0,
                imageUrl = ""
            )
        )
    }
}


@Preview
@Composable
private fun BrochureGridPreview(@PreviewParameter(BrochureGridPreviewParameter::class) brochureList: List<Brochure>) {
    BrochureAppTheme {
//        BrochureGrid(brochures = brochureList)
    }
}


