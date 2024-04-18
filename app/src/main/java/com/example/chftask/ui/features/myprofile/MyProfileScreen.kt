package com.example.chftask.ui.features.myprofile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.chftask.ui.features.myprofile.composable.MyProfileContent
import com.example.chftask.ui.shared.base.BaseScreen

@Composable
fun MyProfileScreen(
    modifier: Modifier = Modifier,
    myProfileViewModel: MyProfileViewModel
) {
    BaseScreen(content = {
        MyProfileContent(
            modifier = modifier,
            user= myProfileViewModel.currentUser.collectAsState().value
        )
    }, viewModel = myProfileViewModel)
}
