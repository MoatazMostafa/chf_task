package com.example.chftask.ui.shared.dialog


import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.chftask.ui.theme.Black

@Composable
fun DialogButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Black,
    onClick: () -> Unit,
) {
    TextButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(text = text, color = textColor)
    }
}