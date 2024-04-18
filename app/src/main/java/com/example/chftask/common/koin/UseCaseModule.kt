package com.example.chftask.common.koin

import com.example.chftask.domain.usecases.user.CacheUserUseCase
import com.example.chftask.domain.usecases.user.CacheUserUseCaseImpl
import com.example.chftask.domain.usecases.user.ClearCachedCurrentUserUseCase
import com.example.chftask.domain.usecases.user.ClearCachedCurrentUserUseCaseImpl
import com.example.chftask.domain.usecases.user.GetCachedCurrentUserUseCase
import com.example.chftask.domain.usecases.user.GetCachedCurrentUserUseCaseImpl
import com.example.chftask.domain.usecases.user.GetCachedUsersUseCase
import com.example.chftask.domain.usecases.user.GetCachedUsersUseCaseImpl
import com.example.chftask.domain.usecases.venue.GetVenuesUseCase
import com.example.chftask.domain.usecases.venue.GetVenuesUseCaseImpl
import org.koin.dsl.module

/**
 * This property is used to create UseCases and inject needed parameters
 * */
val useCaseModule = module {

    factory<GetVenuesUseCase> {
        GetVenuesUseCaseImpl(repository = get())
    }

    factory<GetCachedUsersUseCase> {
        GetCachedUsersUseCaseImpl(localRepository = get())
    }

    factory<GetCachedCurrentUserUseCase> {
        GetCachedCurrentUserUseCaseImpl(localRepository = get())
    }

    factory<CacheUserUseCase> {
        CacheUserUseCaseImpl(localRepository = get())
    }

    factory<ClearCachedCurrentUserUseCase> {
        ClearCachedCurrentUserUseCaseImpl(localRepository = get())
    }
}
