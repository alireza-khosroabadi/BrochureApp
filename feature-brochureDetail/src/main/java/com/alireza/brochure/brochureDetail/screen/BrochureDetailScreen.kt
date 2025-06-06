package com.alireza.brochure.brochureDetail.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alireza.brochure.brochureDetail.viewModel.BrochureDetailViewModel
import com.alireza.brochure.designsystem.component.AsyncImageLoader
import com.alireza.brochure.model.brochureDetail.BrochureDetail
import com.alireza.brochure.model.brochureDetail.StoreLocation
import com.alireza.brochure.ui.component.dashedLine.DashedDivider

@Composable
fun BrochureDetailScreen(
    viewModel: BrochureDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val configuration = LocalConfiguration.current

    LaunchedEffect(Unit) {
        viewModel.loadBrochure()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.clickable { onBackClick.invoke() },
                imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                text = uiState.value?.title ?: "Not Found"
            )
        }

        uiState.value?.let {
            when (configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                    BrochureDetailHorizontal(brochure = it)
                }
                else -> {
                    BrochureDetailVertical(brochure = it)
                }
            }

        }
    }
}