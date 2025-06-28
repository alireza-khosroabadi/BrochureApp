package com.alireza.brochure.brochureDetail.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alireza.brochure.model.brochureDetail.StoreLocation
import com.alireza.brochure.ui.component.dashedLine.DashedDivider


@Composable
fun StoreLocationBox(location: StoreLocation) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        LocationItem("City", location.city)
        LocationItem("Street", location.street)
        LocationItem("Street Number", location.streetNumber)
        LocationItem("Zip Code", location.zipCode)
    }
}

@Composable
private fun LocationItem(title: String, value: String) {
    Row(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
        )

        DashedDivider(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp)
        )

        Text(
            text = value,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
fun ValidDateRow(from: String, to: String) {

    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "From: $from",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .weight(1f)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(8.dp)
        )

        Spacer(Modifier.size(16.dp))

        Text(
            text = "To: $to",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .weight(1f)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(8.dp)
        )

    }

}