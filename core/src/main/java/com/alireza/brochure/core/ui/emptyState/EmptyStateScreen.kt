package com.alireza.brochure.core.ui.emptyState


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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alireza.brochure.core.ui.theme.BrochureAppTheme

@Composable
fun EmptyStateScreen(
    modifier: Modifier = Modifier,
    title: String = "No Item Available",
    subtitle: String = "Try again later or check your connection.",
    onRetry: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .testTag("EmptyState"),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Empty State Icon",
            modifier = Modifier
                .size(96.dp)
                .padding(bottom = 16.dp),
            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
        )

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onRetry,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .height(48.dp)
                .defaultMinSize(minWidth = 140.dp)
        ) {
            Text(text = "Retry")
        }
    }
}


@Preview
@Composable
fun EmptyStateScreenPreview(){
    BrochureAppTheme {
        EmptyStateScreen {  }
    }
}