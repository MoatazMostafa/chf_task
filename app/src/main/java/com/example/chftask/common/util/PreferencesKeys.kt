package com.example.chftask.common.util

import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    const val PREFERENCES_NAME = "CHFPreferences"
    val USERS = stringPreferencesKey("users")
    val USER = stringPreferencesKey("user")
}