package com.example.chftask.ui.features.home.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chftask.ui.features.home.model.VenueUIModel
import com.example.chftask.ui.theme.CHFTaskTheme

@Composable
fun VenuesContent(
    modifier: Modifier,
    venuesList: List<VenueUIModel>
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        venuesList.forEach {
            VenueItem(venue = it)
        }
    }
}

@Composable
fun VenueItem(venue: VenueUIModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = venue.name ?: "",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Location: ${venue.location?.address ?: ""}",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Category: ${venue.categories?.firstOrNull()?.name ?: ""}",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
@Preview
fun VenuesContentPreview() {
    CHFTaskTheme {
        VenuesContent(
            modifier = Modifier,
            venuesList = emptyList()
        )
    }
}