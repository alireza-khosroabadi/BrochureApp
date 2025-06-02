package com.alireza.brochure.feature_brochure.brochure.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.FilterAltOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alireza.brochure.feature_brochure.brochure.state.BrochureUiState
import com.alireza.brochure.feature_brochure.brochure.viewModel.BrochureListViewModel
import com.alireza.brochure.ui.component.loadingScreen.LoadingComponent
import com.alireza.brochure.feature_brochure.R
import com.alireza.brochure.feature_brochure.brochure.ui.previewProvider.BrochureScreenParameterProvider
import com.alireza.brochure.ui.component.emptyState.EmptyStateScreen
import com.alireza.brochure.ui.component.errorScreen.ErrorScreen
import com.alireza.brochure.designsystem.theme.BrochureAppTheme

@Composable
fun BrochureListScreen(viewModel: BrochureListViewModel = hiltViewModel()) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val filterState = viewModel.isFilterActive.collectAsStateWithLifecycle()

    ScreenContent(
        filterState,
        uiState.value,
        onToggleFilter = viewModel::toggleFilter,
        fetchBrochure = viewModel::fetchBrochure
    )
}

@Composable
private fun ScreenContent(
    filterState: State<Boolean>,
    uiState: BrochureUiState,
    onToggleFilter: () -> Unit,
    fetchBrochure: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        FilterRow(
            isFilterActive = filterState.value,
            showFilter = uiState is BrochureUiState.Success
        ) {
            onToggleFilter.invoke()
        }
        when (uiState) {
            is BrochureUiState.Error -> ErrorScreen(appError = uiState.error) { fetchBrochure.invoke() }
            BrochureUiState.Loading -> LoadingComponent()
            is BrochureUiState.Success -> BrochureList(modifier = Modifier, uiState = uiState)
            BrochureUiState.EmptyState -> EmptyStateScreen { fetchBrochure.invoke() }
        }
    }
}


@Composable
private fun BrochureList(modifier: Modifier = Modifier, uiState: BrochureUiState.Success) {
    Column(modifier= modifier.testTag("BrochureGrid")) {
        if (uiState.fromCache) {
            Text(
                stringResource(R.string.BrochureScreen_offline_data),
                modifier = Modifier.padding(8.dp).testTag("OfflineMode")
            )
        }
        BrochureGrid(uiState.brochures)
    }
}


@Composable
private fun FilterRow(
    isFilterActive: Boolean,
    showFilter: Boolean,
    onToggle: () -> Unit
) {
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = stringResource(R.string.brochureScreen_title),
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.weight(1f))
        if (showFilter)
            IconToggleButton(
                modifier = Modifier.size(32.dp),
                checked = isFilterActive, onCheckedChange = { onToggle() }) {
                Icon(
                    imageVector = if (isFilterActive) Icons.Default.FilterAltOff else Icons.Default.FilterAlt,
                    contentDescription = "Filter"
                )
            }
    }
}


@Preview
@Composable
fun BrochureListScreenContentPreview(@PreviewParameter(BrochureScreenParameterProvider::class) uiState: BrochureUiState){

    BrochureAppTheme {
        ScreenContent(filterState = remember { mutableStateOf(false) }, uiState= uiState, onToggleFilter =  {}) { }
    }
}