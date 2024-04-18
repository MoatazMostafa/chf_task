package com.example.chftask.data.datasources.local.cache

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.chftask.common.util.PreferencesKeys
import com.example.chftask.data.datasources.local.user.model.User
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class LocalCachedDataSourceImpl(
    context: Context
) : LocalCachedDataSource {

    private val Context.dataStore by preferencesDataStore(name = PreferencesKeys.PREFERENCES_NAME)
    private val dataStore = context.dataStore
    private val gson = Gson()

    override suspend fun cacheUser(user: User) {
        val usersJson = dataStore.data.map { preferences ->
            preferences[PreferencesKeys.USERS] ?: ""
        }.first()

        val usersList = if (usersJson.isNotEmpty()) {
            gson.fromJson(usersJson, Array<User>::class.java).toList()
        } else {
            emptyList()
        }

        val updatedUsersList = usersList + user
        val updatedUsersJson = gson.toJson(updatedUsersList)

        dataStore.edit { preferences ->
            preferences[PreferencesKeys.USERS] = updatedUsersJson
        }
        val userJson = gson.toJson(user)
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.USER] = userJson
        }
    }

    override suspend fun getCachedUsers(): List<User> {
        val usersJson = dataStore.data.map { preferences ->
            preferences[PreferencesKeys.USERS] ?: ""
        }.first()

        return if (usersJson.isNotEmpty()) {
            gson.fromJson(usersJson, Array<User>::class.java).toList()
        } else {
            emptyList()
        }
    }

    override suspend fun getCachedCurrentUser(): User? {
        val userJson = dataStore.data.map { preferences ->
            preferences[PreferencesKeys.USER]
        }.first()
        return if (userJson != null) gson.fromJson(userJson, User::class.java) else null
    }

    override suspend fun clearCachedCurrentUser() {
        dataStore.edit { preferences ->
            preferences.remove(PreferencesKeys.USER)
        }
    }
}