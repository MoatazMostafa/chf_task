package com.example.chftask.ui.features.host

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chftask.R
import com.example.chftask.common.manager.navigation.ChfTaskNavDestination
import com.example.chftask.ui.features.authentication.AuthenticationScreen
import com.example.chftask.ui.features.authentication.AuthenticationViewModel
import com.example.chftask.ui.features.home.HomeScreen
import com.example.chftask.ui.features.home.HomeViewModel
import com.example.chftask.ui.features.myprofile.MyProfileScreen
import com.example.chftask.ui.features.myprofile.MyProfileViewModel
import com.example.chftask.ui.features.splash.SplashScreen
import com.example.chftask.ui.features.splash.SplashViewModel
import com.example.chftask.ui.features.termsandconditions.TermsAndConditionsScreen
import com.example.chftask.ui.features.termsandconditions.TermsAndConditionsViewModel
import com.example.chftask.ui.shared.composables.TopAppBar
import com.example.chftask.ui.shared.uimodel.TopBarAction
import com.example.chftask.ui.shared.uimodel.TopBarProperties
import com.example.chftask.ui.theme.Secondary
import com.example.chftask.ui.theme.White
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
    navController: NavHostController = rememberNavController(),
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var topBarProperties by remember { mutableStateOf(TopBarProperties(showTopBar = false)) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            Column(
                modifier = Modifier
                    .background(Secondary)
                    .fillMaxHeight()
                    .width(200.dp)
            ) {

                Image(
                    modifier = Modifier.width(200.dp),
                    painter = painterResource(id = R.drawable.cfh_logo),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .align(Alignment.Start)
                        .clickable {
                            if (navController.currentDestination?.route != ChfTaskNavDestination.Home.navComposableDestination) {
                                navController.navigate(ChfTaskNavDestination.Home.navComposableDestination)
                            }
                            scope.launch { drawerState.close() }
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = modifier
                            .weight(1f)
                            .padding(vertical = 16.dp),
                        text = stringResource(R.string.home),
                        style = MaterialTheme.typography.bodyMedium,
                        color = White
                    )
                    Icon(
                        modifier = Modifier,
                        painter = painterResource(id = R.drawable.ic_home),
                        contentDescription = "",
                        tint = White
                    )
                }
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = White
                )
                Row(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .align(Alignment.Start)
                        .clickable {
                            navController.navigate(ChfTaskNavDestination.MyProfile.navComposableDestination)
                            scope.launch { drawerState.close() }
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = modifier
                            .weight(1f)
                            .padding(vertical = 16.dp),
                        text = stringResource(R.string.my_profile),
                        style = MaterialTheme.typography.bodyMedium,
                        color = White
                    )
                    Icon(
                        modifier = Modifier,
                        painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = "",
                        tint = White
                    )
                }
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = White
                )
                Row(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .align(Alignment.Start)
                        .clickable {
                            navController.navigate(ChfTaskNavDestination.TermsAndConditions.navComposableDestination)
                            scope.launch { drawerState.close() }
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = modifier
                            .weight(1f)
                            .padding(vertical = 16.dp),
                        text = stringResource(R.string.terms_and_conditions),
                        style = MaterialTheme.typography.bodyMedium,
                        color = White
                    )
                    Icon(
                        modifier = Modifier,
                        painter = painterResource(id = R.drawable.ic_terms_conditions),
                        contentDescription = "",
                        tint = White
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .align(Alignment.Start)
                        .clickable {
                            mainViewModel.logout()
                            navController.popBackStack()
                            navController.navigate(ChfTaskNavDestination.Authentication.navComposableDestination)
                            scope.launch { drawerState.close() }
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = modifier
                            .weight(1f)
                            .padding(vertical = 16.dp),
                        text = stringResource(R.string.logout),
                        style = MaterialTheme.typography.bodyMedium,
                        color = White
                    )
                    Icon(
                        modifier = Modifier,
                        painter = painterResource(id = R.drawable.ic_log_out),
                        contentDescription = "",
                        tint = White
                    )
                }
            }
        },
        content = {
            Scaffold(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .navigationBarsPadding(),
                topBar = {
                    TopAppBar(
                        showTopBar = topBarProperties.showTopBar,
                        showLogo = topBarProperties.showLogo,
                        showBackButton = topBarProperties.showBackButton,
                        topBarActionList = topBarProperties.topBarActionList,
                        onTopBarActionClick = topBarProperties.topBarActionClick,
                        onBackButtonClick = topBarProperties.backButtonClick,
                    )
                }
            )
            { innerPadding ->
                Column(modifier = modifier.zIndex(100f)) {
                    NavHost(
                        navController = navController,
                        startDestination = ChfTaskNavDestination.Splash.navComposableDestination,
                        enterTransition = {
                            slideInHorizontally(
                                initialOffsetX = { 1000 },
                                animationSpec = tween(500)
                            )
                        }
                    ) {
                        composable(route = ChfTaskNavDestination.Splash.navComposableDestination) {
                            val splashViewModel: SplashViewModel =
                                koinViewModel<SplashViewModel>().apply {
                                    updateNavController(navController = navController)
                                    parametersOf(navController)
                                }
                            SplashScreen(
                                modifier = Modifier.paddedModifier(
                                    innerPaddingValues = innerPadding,
                                    showToolbar = false,
                                ),
                                splashViewModel = splashViewModel
                            )
                        }

                        composable(route = ChfTaskNavDestination.Authentication.navComposableDestination) {
                            val authenticationViewModel: AuthenticationViewModel =
                                koinViewModel<AuthenticationViewModel>().apply {
                                    updateNavController(navController = navController)
                                    parametersOf(navController)
                                }
                            AuthenticationScreen(
                                modifier = Modifier.paddedModifier(
                                    innerPaddingValues = innerPadding,
                                    showToolbar = false,
                                ),
                                authenticationViewModel = authenticationViewModel
                            )
                        }

                        composable(route = ChfTaskNavDestination.Home.navComposableDestination) {
                            val homeViewModel: HomeViewModel =
                                koinViewModel<HomeViewModel>().apply {
                                    updateNavController(navController = navController)
                                    parametersOf(navController)
                                }
                            topBarProperties = TopBarProperties(
                                showTopBar = true,
                                showLogo = true,
                                topBarActionList = listOf(TopBarAction.Menu),
                                topBarActionClick = { topBarAction ->
                                    when (topBarAction) {
                                        TopBarAction.Menu -> scope.launch { drawerState.open() }
                                        else -> {}
                                    }
                                }
                            )
                            HomeScreen(
                                modifier = Modifier.paddedModifier(
                                    innerPaddingValues = innerPadding,
                                    showToolbar = true,
                                ),
                                homeViewModel = homeViewModel
                            )
                        }

                        composable(route = ChfTaskNavDestination.MyProfile.navComposableDestination) {
                            val myProfileViewModel: MyProfileViewModel =
                                koinViewModel<MyProfileViewModel>().apply {
                                    updateNavController(navController = navController)
                                    parametersOf(navController)
                                }
                            topBarProperties = TopBarProperties(
                                showTopBar = true,
                                showBackButton = true,
                                backButtonClick = {
                                    navController.popBackStack()
                                }
                            )
                            MyProfileScreen(
                                modifier = Modifier.paddedModifier(
                                    innerPaddingValues = innerPadding,
                                    showToolbar = true,
                                ),
                                myProfileViewModel = myProfileViewModel
                            )
                        }

                        composable(route = ChfTaskNavDestination.TermsAndConditions.navComposableDestination) {
                            val termsAndConditionsViewModel: TermsAndConditionsViewModel =
                                koinViewModel<TermsAndConditionsViewModel>().apply {
                                    updateNavController(navController = navController)
                                    parametersOf(navController)
                                }
                            topBarProperties = TopBarProperties(
                                showTopBar = true,
                                showBackButton = true,
                                backButtonClick = {
                                    navController.popBackStack()
                                }
                            )
                            TermsAndConditionsScreen(
                                modifier = Modifier.paddedModifier(
                                    innerPaddingValues = innerPadding,
                                    showToolbar = true,
                                ),
                                termsAndConditionsViewModel = termsAndConditionsViewModel
                            )
                        }
                    }
                }
            }
        }
    )
}


private fun Modifier.paddedModifier(
    innerPaddingValues: PaddingValues,
    showToolbar: Boolean
): Modifier {
    val padding = PaddingValues(
        top = if (showToolbar) innerPaddingValues.calculateTopPadding() else 0.dp,
    )
    return then(Modifier.padding(padding))
}


