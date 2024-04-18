package com.example.chftask.ui.features.termsandconditions

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.chftask.ui.features.termsandconditions.composable.TermsAndConditionsContent
import com.example.chftask.ui.shared.base.BaseScreen

@Composable
fun TermsAndConditionsScreen(
    modifier: Modifier = Modifier,
    termsAndConditionsViewModel: TermsAndConditionsViewModel
) {
    BaseScreen(content = {
        TermsAndConditionsContent(modifier = modifier)
    }, viewModel = termsAndConditionsViewModel)
}