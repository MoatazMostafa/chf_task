package com.example.chftask.common.manager.navigation

import com.example.chftask.common.manager.navigation.destination.AuthenticationNavDestination
import com.example.chftask.common.manager.navigation.destination.HomeNavDestination
import com.example.chftask.common.manager.navigation.destination.MyProfileNavDestination
import com.example.chftask.common.manager.navigation.destination.SplashNavDestination
import com.example.chftask.common.manager.navigation.destination.TermsAndConditionsNavDestination

object ChfTaskNavDestination {

    val Splash = SplashNavDestination
    val Authentication = AuthenticationNavDestination
    val Home = HomeNavDestination
    val MyProfile = MyProfileNavDestination
    val TermsAndConditions = TermsAndConditionsNavDestination
}


interface Route {
    val route: String
}
