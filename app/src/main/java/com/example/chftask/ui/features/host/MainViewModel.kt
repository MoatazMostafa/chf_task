package com.example.chftask.ui.features.host

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.chftask.domain.usecases.user.ClearCachedCurrentUserUseCase
import com.example.chftask.ui.shared.base.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application,
    private val clearCachedCurrentUserUseCase: ClearCachedCurrentUserUseCase
) : BaseViewModel(
    application = application,
) {
    fun logout() {
        viewModelScope.launch {
            clearCachedCurrentUserUseCase()
        }
    }
}