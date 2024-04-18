package com.example.chftask.ui.features.termsandconditions.composable

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.chftask.R

@Composable
fun TermsAndConditionsContent(
    modifier: Modifier = Modifier,
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                loadUrl(context.getString(R.string.google_terms_url))
            }
        }
    )
}