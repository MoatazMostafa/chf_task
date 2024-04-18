package com.example.chftask.common.koin


import com.example.chftask.data.repository.local.LocalRepository
import com.example.chftask.data.repository.local.LocalRepositoryImpl
import com.example.chftask.data.repository.venues.VenueRepository
import com.example.chftask.data.repository.venues.VenueRepositoryImpl
import org.koin.dsl.module

/**
 * This property is used to create Repositories and inject needed parameters
 * */
val repositoryModule = module {

    single<VenueRepository> {
        VenueRepositoryImpl(
            getVenuesDataSource = get()
        )
    }

    single<LocalRepository> {
        LocalRepositoryImpl(
            localCachedDataSource = get()
        )
    }
}