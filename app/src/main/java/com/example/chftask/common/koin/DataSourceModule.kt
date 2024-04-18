package com.example.chftask.common.koin


import com.example.chftask.data.datasources.local.cache.LocalCachedDataSource
import com.example.chftask.data.datasources.local.cache.LocalCachedDataSourceImpl
import com.example.chftask.data.datasources.remote.venues.GetVenuesDataSource
import com.example.chftask.data.datasources.remote.venues.GetVenuesDataSourceImpl
import org.koin.dsl.module

/**
 * This property is used to create Repositories and inject needed parameters
 * */
val dataSourceModule = module {

    single<GetVenuesDataSource> {
        GetVenuesDataSourceImpl(configService = get())
    }

    single<LocalCachedDataSource> {
        LocalCachedDataSourceImpl(context = get())
    }
}
