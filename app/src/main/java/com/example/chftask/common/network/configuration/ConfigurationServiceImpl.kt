package com.example.chftask.common.network.configuration

import android.content.Context
import com.example.chftask.R

class ConfigurationServiceImpl (private val context: Context) :
    ConfigurationService {
    override val baseUrl: String
        get() = context.getString(R.string.base_url)
}