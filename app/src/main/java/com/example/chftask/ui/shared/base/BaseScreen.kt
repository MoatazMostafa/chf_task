package com.example.chftask.ui.shared.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.chftask.ui.shared.dialog.MessageDialog

@Composable
fun BaseScreen(
    content: @Composable () -> Unit,
    viewModel: BaseViewModel,
) {
    content()
    MessageDialog(
        dialogTexts = viewModel.generalError.collectAsState().value,
        onConfirm = { viewModel.resetGeneralError() },
        onDismiss = {}
    )
}
