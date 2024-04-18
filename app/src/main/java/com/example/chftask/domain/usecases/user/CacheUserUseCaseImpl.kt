package com.example.chftask.domain.usecases.user

import com.example.chftask.data.repository.local.LocalRepository
import com.example.chftask.domain.model.UserDomainModel
import com.example.chftask.domain.model.toDataModel

class CacheUserUseCaseImpl(
    private val localRepository: LocalRepository
): CacheUserUseCase{
    override suspend fun invoke(user: UserDomainModel) {
        localRepository.cacheUser(user.toDataModel())
    }
}