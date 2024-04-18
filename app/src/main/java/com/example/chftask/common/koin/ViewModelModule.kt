package com.example.chftask.common.koin

import com.example.chftask.ui.features.authentication.AuthenticationViewModel
import com.example.chftask.ui.features.home.HomeViewModel
import com.example.chftask.ui.features.host.MainViewModel
import com.example.chftask.ui.features.myprofile.MyProfileViewModel
import com.example.chftask.ui.features.splash.SplashViewModel
import com.example.chftask.ui.features.termsandconditions.TermsAndConditionsViewModel
import com.example.chftask.ui.shared.base.BaseViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * This property is used to create ViewModels and inject needed parameters
 * */
val viewModelModule = module {
    viewModel {
        BaseViewModel(application = androidApplication())
    }

    viewModel {
        MainViewModel(application = androidApplication(), clearCachedCurrentUserUseCase = get())
    }

    viewModel {
        SplashViewModel(application = androidApplication(), getCachedCurrentUserUseCase = get())
    }

    viewModel {
        AuthenticationViewModel(
            application = androidApplication(),
            getCachedUserUseCase = get(),
            cacheUserUseCase = get()
        )
    }

    viewModel {
        HomeViewModel(application = androidApplication(), getVenuesUseCase = get())
    }

    viewModel {
        MyProfileViewModel(application = androidApplication(), getCachedCurrentUserUseCase = get())
    }

    viewModel{
        TermsAndConditionsViewModel(application = androidApplication())
    }
}
