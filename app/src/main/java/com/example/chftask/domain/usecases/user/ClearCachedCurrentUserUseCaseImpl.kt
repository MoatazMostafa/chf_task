package com.example.chftask.domain.usecases.user

import com.example.chftask.data.repository.local.LocalRepository

class ClearCachedCurrentUserUseCaseImpl(
    private val localRepository: LocalRepository
): ClearCachedCurrentUserUseCase{
    override suspend fun invoke() {
        localRepository.clearCachedCurrentUser()
    }
}