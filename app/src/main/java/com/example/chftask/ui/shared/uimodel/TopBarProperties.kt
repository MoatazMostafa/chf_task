package com.example.chftask.ui.shared.uimodel

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable

data class TopBarProperties(
    val showTopBar: Boolean = false,
    val showLogo: Boolean = false,
    val showBackButton: Boolean = false,
    val topBarActionList: List<TopBarAction> = emptyList(),
    val title: String? = null,
    val backNavigationAction: (() -> Unit)? = null,
    val actions: @Composable RowScope.() -> Unit = {},
    val backButtonClick: () -> Unit = {},
    val topBarActionClick: (TopBarAction) -> Unit = {}
)