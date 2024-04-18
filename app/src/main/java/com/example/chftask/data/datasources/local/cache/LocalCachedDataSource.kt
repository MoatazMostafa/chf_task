package com.example.chftask.data.datasources.local.cache

import com.example.chftask.data.datasources.local.user.model.User

interface LocalCachedDataSource {
    suspend fun cacheUser(user: User)
    suspend fun getCachedUsers(): List<User>
    suspend fun getCachedCurrentUser(): User?
    suspend fun clearCachedCurrentUser()
}