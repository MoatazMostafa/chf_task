package com.example.chftask.domain.usecases.user

import com.example.chftask.data.repository.local.LocalRepository
import com.example.chftask.domain.model.UserDomainModel
import com.example.chftask.domain.model.toDomainModel

class GetCachedUsersUseCaseImpl(
    private val localRepository: LocalRepository
) : GetCachedUsersUseCase {
    override suspend fun invoke(): List<UserDomainModel> =
        localRepository.getCachedUsers().map { it.toDomainModel() }
}