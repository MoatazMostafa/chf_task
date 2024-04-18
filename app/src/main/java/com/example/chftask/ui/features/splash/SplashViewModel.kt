package com.example.chftask.ui.features.splash

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.chftask.common.manager.navigation.ChfTaskNavDestination
import com.example.chftask.domain.model.UserDomainModel
import com.example.chftask.domain.usecases.user.GetCachedCurrentUserUseCase
import com.example.chftask.ui.shared.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    application: Application,
    val getCachedCurrentUserUseCase: GetCachedCurrentUserUseCase
) : BaseViewModel(
    application = application,
) {
    private var _isScreenClosed = MutableStateFlow(false)
    private var currentUser: UserDomainModel? = null

    init {
        viewModelScope.launch {
            currentUser = getCachedCurrentUserUseCase()
        }
    }


    fun onStartSplash() {
        viewModelScope.launch {
            delay(3000)
            navController?.popBackStack()
            if (currentUser == null) {
                navController?.navigate(route = ChfTaskNavDestination.Authentication.navComposableDestination)
            } else {
                navController?.navigate(route = ChfTaskNavDestination.Home.navComposableDestination)

            }
        }
    }

    fun onStopSplash() {
        _isScreenClosed.value = true
    }
}