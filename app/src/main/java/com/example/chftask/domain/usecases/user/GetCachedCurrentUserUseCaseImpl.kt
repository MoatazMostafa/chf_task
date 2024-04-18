package com.example.chftask.domain.usecases.user

import com.example.chftask.data.repository.local.LocalRepository
import com.example.chftask.domain.model.UserDomainModel
import com.example.chftask.domain.model.toDomainModel

class GetCachedCurrentUserUseCaseImpl(
    private val localRepository: LocalRepository
) : GetCachedCurrentUserUseCase {
    override suspend fun invoke(): UserDomainModel? =
        localRepository.getCachedCurrentUser()?.toDomainModel()
}