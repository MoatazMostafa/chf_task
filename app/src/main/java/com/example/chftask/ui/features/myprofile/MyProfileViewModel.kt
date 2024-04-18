package com.example.chftask.ui.features.myprofile

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.chftask.domain.usecases.user.GetCachedCurrentUserUseCase
import com.example.chftask.ui.shared.base.BaseViewModel
import com.example.chftask.ui.shared.uimodel.UserUIModel
import com.example.chftask.ui.shared.uimodel.toUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyProfileViewModel(
    application: Application,
    val getCachedCurrentUserUseCase: GetCachedCurrentUserUseCase
) : BaseViewModel(
    application = application,
) {
    private val _currentUser = MutableStateFlow(
        UserUIModel(
            id = "",
            firstName = "",
            lastName = "",
            age = "",
            email = "",
            password = ""
        )
    )
    val currentUser = _currentUser.asStateFlow()

    init {
        viewModelScope.launch {
            _currentUser.value = getCachedCurrentUserUseCase()?.toUIModel() ?: _currentUser.value
        }
    }
}