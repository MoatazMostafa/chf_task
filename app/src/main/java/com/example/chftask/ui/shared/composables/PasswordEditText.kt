package com.example.chftask.ui.shared.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.chftask.R
import com.example.chftask.ui.theme.BackgroundGray
import com.example.chftask.ui.theme.Black
import com.example.chftask.ui.theme.Gray
import com.example.chftask.ui.theme.LightGray

@Composable
fun PasswordEditText(
    modifier: Modifier = Modifier,
    hint: String = "",
    text: MutableState<String> = remember { mutableStateOf("") },
    isError: Boolean = false,
) {
    var passwordVisibility by remember { mutableStateOf(false) }

    TextField(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        value = text.value,
        onValueChange = { text.value = it },
        placeholder = {
            Text(
                text = hint,
                style = MaterialTheme.typography.bodyMedium,
                color = Gray
            )
        },
        textStyle = MaterialTheme.typography.bodyMedium,
        isError = isError,
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(
                    imageVector = if (passwordVisibility)
                        ImageVector.vectorResource(R.drawable.ic_password_show)
                    else
                        ImageVector.vectorResource(R.drawable.ic_password_hide),
                    contentDescription = if (passwordVisibility) "Hide password" else "Show password"
                )
            }
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = BackgroundGray,
            focusedContainerColor = BackgroundGray,
            disabledContainerColor = LightGray,
            cursorColor = Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        )
    )
}