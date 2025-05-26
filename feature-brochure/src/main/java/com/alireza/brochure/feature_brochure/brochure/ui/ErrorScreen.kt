package com.alireza.brochure.feature_brochure.brochure.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alireza.brochure.core.ui.theme.BrochureAppTheme
import com.alireza.brochureApp.common.model.appError.AppError

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    appError: AppError,
    onRetryClick: () -> Unit
) {
    val errorMessage = when (appError) {
        AppError.NoInternet -> "No internet connection. Please check your network."
        is AppError.ServerError -> appError.message ?: "Server error. Try again later."
        AppError.Timeout -> "Request timed out. Please retry."
        is AppError.Unknown -> appError.message ?: "Something went wrong."
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .testTag("ErrorScreen"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            imageVector = Icons.Default.Error,
            contentDescription = "Empty State Icon",
            modifier = Modifier
                .size(96.dp)
                .padding(bottom = 16.dp),
            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
        )

        Text(
            text = errorMessage,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onRetryClick,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .height(48.dp)
                .defaultMinSize(minWidth = 140.dp)
        )  {
            Text(text = "Retry")
        }
    }
}

@Preview
@Composable
fun ErrorScreenPreview(){
    BrochureAppTheme {
        ErrorScreen(appError = AppError.NoInternet){}
    }
}
