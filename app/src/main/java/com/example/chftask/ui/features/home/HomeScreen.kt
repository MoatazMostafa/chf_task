package com.example.chftask.ui.features.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.chftask.ui.features.home.composable.HomeContent
import com.example.chftask.ui.shared.base.BaseScreen
import com.example.chftask.ui.shared.composables.loading.GeneralLoading
import com.example.chftask.ui.shared.uimodel.LoadingState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel
) {
    BaseScreen(
        content =
        {
            when (homeViewModel.loadingStateValue.collectAsState().value) {
                LoadingState.LOADING -> GeneralLoading(modifier = modifier.fillMaxSize())
                else -> {
                    HomeContent(
                        modifier = modifier,
                        selectedTabIndex = homeViewModel.selectedTabIndex.collectAsState().value,
                        venuesList = homeViewModel.venuesList.collectAsState().value,
                        onTabClick = homeViewModel::onTabClick,
                        onPermissionGranted = homeViewModel::onPermissionGranted,
                        onPermissionDenied = homeViewModel::onPermissionDenied

                    )
                }
            }
        },
        viewModel = homeViewModel
    )
}