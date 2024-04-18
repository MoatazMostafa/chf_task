package com.example.chftask.data.repository.local

import com.example.chftask.data.datasources.local.cache.LocalCachedDataSource
import com.example.chftask.data.datasources.local.user.model.User

class LocalRepositoryImpl(
    private val localCachedDataSource: LocalCachedDataSource
) : LocalRepository {
    override suspend fun cacheUser(user: User) {
        localCachedDataSource.cacheUser(user)
    }

    override suspend fun getCachedUsers(): List<User> {
        return localCachedDataSource.getCachedUsers()
    }

    override suspend fun getCachedCurrentUser(): User? {
        return localCachedDataSource.getCachedCurrentUser()
    }

    override suspend fun clearCachedCurrentUser() {
        localCachedDataSource.clearCachedCurrentUser()
    }
}