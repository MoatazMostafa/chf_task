package com.example.chftask.ui.features.authentication

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.chftask.R
import com.example.chftask.common.manager.navigation.ChfTaskNavDestination
import com.example.chftask.domain.usecases.user.CacheUserUseCase
import com.example.chftask.domain.usecases.user.GetCachedUsersUseCase
import com.example.chftask.ui.features.authentication.model.CurrentAuthenticationContent
import com.example.chftask.ui.features.authentication.model.LoginUIModel
import com.example.chftask.ui.features.home.model.VenueUIModel
import com.example.chftask.ui.shared.base.BaseViewModel
import com.example.chftask.ui.shared.uimodel.UserUIModel
import com.example.chftask.ui.shared.uimodel.toDomainModel
import com.example.chftask.ui.shared.uimodel.toUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AuthenticationViewModel(
    application: Application,
    val getCachedUserUseCase: GetCachedUsersUseCase,
    val cacheUserUseCase: CacheUserUseCase
) : BaseViewModel(
    application = application,
) {
    private var _currentAuthenticationContent = MutableStateFlow(CurrentAuthenticationContent.LOGIN)
    val currentAuthenticationContent = _currentAuthenticationContent.asStateFlow()
    private var usersList = MutableStateFlow<List<UserUIModel>>(emptyList())
    fun onLoginClick(loginUIModel: LoginUIModel) {
        viewModelScope.launch {
            usersList.value = getCachedUserUseCase().map { it.toUIModel() }
            usersList.collect { users ->
                val user =
                    users.find { it.email == loginUIModel.email && it.password == loginUIModel.password }
                if (user != null) {
                    cacheUserUseCase(user.toDomainModel())
                    navController?.popBackStack()
                    navController?.navigate(ChfTaskNavDestination.Home.navComposableDestination)
                } else {
                    handleLocalError(
                        errorTitle = application.getString(R.string.invalid_credentials),
                        errorMessage = application.getString(R.string.wrong_email_or_password_please_try_again)
                    )
                }
            }
        }

    }

    fun onSignUpClick() {
        _currentAuthenticationContent.value = CurrentAuthenticationContent.REGISTER
    }

    fun onRegisterClick(userUIModel: UserUIModel) {
        viewModelScope.launch {
            cacheUserUseCase(userUIModel.toDomainModel())
            navController?.popBackStack()
            navController?.navigate(ChfTaskNavDestination.Home.navComposableDestination)
        }
    }
}