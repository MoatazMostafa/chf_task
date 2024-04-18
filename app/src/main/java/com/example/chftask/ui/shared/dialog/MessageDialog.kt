package com.example.chftask.ui.shared.dialog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.chftask.R
import com.example.chftask.ui.shared.uimodel.DialogTexts
import com.example.chftask.ui.theme.CHFTaskTheme


@Composable
fun MessageDialog(
    modifier: Modifier = Modifier,
    dialogTexts: DialogTexts?,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    dialogTexts?.let {
        AlertDialog(
            modifier = modifier.fillMaxWidth(),
            title = { Text(text = dialogTexts.title) },
            text = { Text(text = dialogTexts.message) },
            onDismissRequest = onDismiss,
            confirmButton = {
                DialogButton(
                    text = stringResource(id = R.string.ok),
                    onClick = onConfirm
                )
            }
        )
    }
}

@Preview
@Composable
private fun MessageDialogPreview() {
    CHFTaskTheme {
        MessageDialog(
            dialogTexts = DialogTexts(title = "Error", message = "Unknown error."),
            onConfirm = {},
            onDismiss = {}
        )
    }
}