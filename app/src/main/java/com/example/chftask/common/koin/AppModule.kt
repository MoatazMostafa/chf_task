package com.example.chftask.common.koin

import com.example.chftask.common.network.configuration.ConfigurationService
import com.example.chftask.common.network.configuration.ConfigurationServiceImpl
import org.koin.dsl.module

val appModule = module {
   single<ConfigurationService> { ConfigurationServiceImpl(get()) }
}