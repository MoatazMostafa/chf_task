package com.example.chftask.ui.features.home.composable

import MapContent
import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.chftask.ui.features.home.model.HomeTab
import com.example.chftask.ui.features.home.model.VenueUIModel
import com.example.chftask.ui.shared.composables.TabContent
import com.example.chftask.ui.theme.CHFTaskTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeContent(
    modifier: Modifier,
    selectedTabIndex: Int,
    venuesList: List<VenueUIModel>,
    onTabClick: (HomeTab) -> Unit,
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit
) {
    val selectedTab = remember { mutableStateOf(HomeTab.Venues) }
    val permissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            onPermissionGranted()
        } else {
            onPermissionDenied()
        }
    }
    DisposableEffect(permissionState) {
        if (!permissionState.status.isGranted) {
            launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        onDispose { }
    }
    Column(modifier = modifier.fillMaxSize()) {
        TabContent(
            tabsList = HomeTab.entries.map { it.name },
            selectedTabIndex = selectedTabIndex,
            onTabClick = {
                selectedTab.value = HomeTab.fromString(it)
                onTabClick(selectedTab.value)
            }
        )
        when (selectedTab.value) {
            HomeTab.Venues -> {
                VenuesContent(
                    modifier = Modifier,
                    venuesList = venuesList
                )
            }

            HomeTab.Map -> {
                MapContent(
                    modifier = Modifier,
                    venuesList = venuesList
                )
            }
        }
    }
}

@Composable
@Preview
fun HomeContentPreview() {
    CHFTaskTheme {
        HomeContent(
            modifier = Modifier,
            selectedTabIndex = 0,
            onTabClick = {},
            onPermissionGranted = {},
            onPermissionDenied = {},
            venuesList = listOf(
                VenueUIModel(
                    name = "Venue 1",
                    location = VenueUIModel.LocationUIModel(
                        address = "Address 1",
                        lat = 0.0,
                        lng = 0.0,
                        labeledLatLngs = emptyList(),
                        distance = 0,
                        postalCode = "123",
                        cc = "CC",
                        city = "City",
                        state = "State",
                        country = "Country",
                        formattedAddress = emptyList(),
                        crossStreet = "Cross Street"
                    ),
                    categories = listOf(
                        VenueUIModel.CategoryUIModel(
                            id = "1",
                            name = "Category 1",
                            pluralName = "Plural Name 1",
                            shortName = "Short Name 1",
                            icon = VenueUIModel.CategoryUIModel.IconUIModel(
                                prefix = "Prefix 1",
                                suffix = "Suffix 1"
                            ),
                            categoryCode = 1,
                            mapIcon = "Map Icon 1",
                            primary = true
                        )
                    ),
                    createdAt = 0,
                    id = "1"
                )
            )
        )
    }
}
