package com.example.chftask.ui.features.authentication.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chftask.R
import com.example.chftask.ui.features.authentication.model.LoginUIModel
import com.example.chftask.ui.theme.CHFTaskTheme
import com.example.chftask.ui.shared.composables.CustomEditText
import com.example.chftask.ui.shared.composables.PasswordEditText
import com.example.chftask.ui.theme.Secondary
import com.example.chftask.ui.theme.White

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    onLoginClick: (LoginUIModel) -> Unit,
    onSignUpClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val loginButtonClicked = remember { mutableStateOf(false) }

        Spacer(modifier = Modifier.height(100.dp))
        Image(
            modifier = Modifier.width(200.dp),
            painter = painterResource(id = R.drawable.cfh_logo),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(32.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodySmall,
            text = stringResource(R.string.email),
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomEditText(
            hint = stringResource(R.string.enter_your_email),
            text = email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = email.value.isEmpty() && loginButtonClicked.value
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodySmall,
            text = stringResource(R.string.password),
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(8.dp))
        PasswordEditText(
            hint = stringResource(id = R.string.enter_your_password),
            text = password,
            isError = password.value.isEmpty() && loginButtonClicked.value
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(modifier = Modifier
            .height(52.dp)
            .fillMaxWidth(),
            onClick = {
                loginButtonClicked.value = true
                if (email.value.isNotBlank() && password.value.isNotBlank()) {
                    onLoginClick(
                        LoginUIModel(
                            email = email.value,
                            password = password.value
                        )
                    )
                }
            }) {
            Text(
                modifier = Modifier,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary,
                text = stringResource(R.string.login)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .height(52.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Secondary,
                contentColor = White
            ),
            onClick = onSignUpClick
        ) {
            Text(
                modifier = Modifier,
                style = MaterialTheme.typography.bodySmall,
                color = White,
                text = stringResource(R.string.sign_up)
            )
        }
    }
}

@Preview
@Composable
fun LoginContentPreview() {
    CHFTaskTheme {
        LoginContent(
            onLoginClick = {},
            onSignUpClick = {}
        )
    }
}