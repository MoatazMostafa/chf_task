package com.example.chftask.data.repository.local

import com.example.chftask.data.datasources.local.user.model.User

interface LocalRepository {
    suspend fun cacheUser(user: User)
    suspend fun getCachedUsers(): List<User>
    suspend fun getCachedCurrentUser(): User?
    suspend fun clearCachedCurrentUser()
}