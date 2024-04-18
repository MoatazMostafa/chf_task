package com.example.chftask.ui.features.authentication

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.chftask.ui.features.authentication.composable.LoginContent
import com.example.chftask.ui.features.authentication.composable.RegisterContent
import com.example.chftask.ui.features.authentication.model.CurrentAuthenticationContent
import com.example.chftask.ui.shared.base.BaseScreen
import com.example.chftask.ui.shared.composables.loading.GeneralLoading
import com.example.chftask.ui.shared.uimodel.LoadingState

@Composable
fun AuthenticationScreen(
    modifier: Modifier = Modifier,
    authenticationViewModel: AuthenticationViewModel
) {
    BaseScreen(
        content =
        {
            when (authenticationViewModel.loadingStateValue.collectAsState().value) {
                LoadingState.LOADING -> GeneralLoading(modifier = modifier.fillMaxSize())
                else -> {
                    Crossfade(
                        targetState = authenticationViewModel.currentAuthenticationContent.collectAsState().value,
                        label = ""
                    ) { screen ->
                        when (screen) {
                            CurrentAuthenticationContent.LOGIN -> {
                                LoginContent(
                                    modifier = modifier,
                                    onLoginClick = authenticationViewModel::onLoginClick,
                                    onSignUpClick = authenticationViewModel::onSignUpClick
                                )
                            }

                            CurrentAuthenticationContent.REGISTER -> {
                                RegisterContent(
                                    modifier = modifier,
                                    onRegisterClick = authenticationViewModel::onRegisterClick,
                                )
                            }
                        }
                    }
                }
            }
        },
        viewModel = authenticationViewModel
    )
}