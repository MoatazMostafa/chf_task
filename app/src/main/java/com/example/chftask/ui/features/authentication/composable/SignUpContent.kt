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
import com.example.chftask.ui.shared.composables.CustomEditText
import com.example.chftask.ui.shared.composables.PasswordEditText
import com.example.chftask.ui.shared.uimodel.UserUIModel
import com.example.chftask.ui.theme.CHFTaskTheme
import com.example.chftask.ui.theme.DarkGray
import com.example.chftask.ui.theme.Secondary
import com.example.chftask.ui.theme.White
import java.util.UUID

@Composable

fun RegisterContent(
    modifier: Modifier = Modifier,
    onRegisterClick: (UserUIModel) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val firstName = remember { mutableStateOf("") }
        val lastName = remember { mutableStateOf("") }
        val age = remember { mutableStateOf("") }
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val passwordPattern =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$".toRegex()
        val isPasswordValid = remember { mutableStateOf(false) }
        val isAgeValid = remember { mutableStateOf(false) }

        val signUpButtonClicked = remember { mutableStateOf(false) }
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            modifier = Modifier.width(150.dp),
            painter = painterResource(id = R.drawable.cfh_logo),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodySmall,
            text = stringResource(R.string.first_name),
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomEditText(
            hint = stringResource(R.string.enter_your_first_name),
            text = firstName,
            isError = firstName.value.isEmpty() && signUpButtonClicked.value
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodySmall,
            text = stringResource(R.string.last_name),
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomEditText(
            hint = stringResource(R.string.enter_your_last_name),
            text = lastName,
            isError = lastName.value.isEmpty() && signUpButtonClicked.value
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodySmall,
            text = stringResource(R.string.age),
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomEditText(
            hint = stringResource(R.string.enter_your_age),
            text = age,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = signUpButtonClicked.value && !isAgeValid.value
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.labelSmall,
            text = stringResource(R.string.must_be_18_or_above),
            textAlign = TextAlign.Start,
            color = DarkGray
        )
        Spacer(modifier = Modifier.height(16.dp))
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
            isError = email.value.isEmpty() && signUpButtonClicked.value
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
            hint = stringResource(R.string.enter_your_password),
            text = password,
            isError = signUpButtonClicked.value && !isPasswordValid.value
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.labelSmall,
            text = stringResource(R.string.password_criteria),
            textAlign = TextAlign.Start,
            color = DarkGray
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            modifier = Modifier
                .height(52.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Secondary,
                contentColor = White
            ),
            onClick = {
                isPasswordValid.value = password.value.matches(passwordPattern)
                isAgeValid.value = age.value.toIntOrNull()?.let { it >= 18 } ?: false
                signUpButtonClicked.value = true
                if (firstName.value.isNotBlank() &&
                    lastName.value.isNotBlank() &&
                    age.value.isNotBlank() &&
                    email.value.isNotBlank() &&
                    password.value.isNotBlank() &&
                    isPasswordValid.value &&
                    isAgeValid.value
                ) {
                    onRegisterClick(
                        UserUIModel(
                            id = UUID.randomUUID().toString(),
                            firstName = firstName.value,
                            lastName = lastName.value,
                            age = age.value,
                            email = email.value,
                            password = password.value
                        )
                    )
                }
            }
        ) {
            Text(
                modifier = Modifier,
                style = MaterialTheme.typography.bodySmall,
                color = White,
                text = stringResource(R.string.register)
            )
        }
    }
}

@Preview
@Composable
fun SignUpContentPreview() {
    CHFTaskTheme {
        RegisterContent {

        }
    }
}