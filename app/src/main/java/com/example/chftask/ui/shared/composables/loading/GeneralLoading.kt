package com.example.chftask.ui.shared.composables.loading

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import com.example.chftask.ui.theme.Black
import com.example.chftask.ui.theme.CHFTaskTheme
import com.example.chftask.ui.theme.White


@Composable
fun GeneralLoading(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .pointerInput(Unit) {},
        contentAlignment = Alignment.Center
    ) {
        FlashDotLoadingIndicator()
    }
}

@Preview
@Composable
private fun GeneralLoadingPreview() {
    CHFTaskTheme {
        GeneralLoading()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun GeneralLoadingDarkModePreview() {
    CHFTaskTheme {
        GeneralLoading()
    }
}