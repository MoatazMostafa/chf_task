package com.example.chftask.common.manager.navigation

import androidx.navigation.NamedNavArgument

interface NavComposableDestination {
    val navComposableDestination: String
    fun getArguments(): List<NamedNavArgument> = emptyList()
}