package com.example.chftask.ui.features.host

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.chftask.ui.shared.base.BaseViewModel
import com.example.chftask.ui.theme.CHFTaskTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModel()
    private var navController: NavController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CHFTaskTheme {
                navController = rememberNavController().also { _navController ->
                    viewModel.updateNavController(_navController)
                    Box(modifier = Modifier.fillMaxSize()) {
                        MainScreen(
                            navController = _navController,
                            mainViewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}
