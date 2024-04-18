package com.example.chftask.domain.usecases.user

import com.example.chftask.domain.model.UserDomainModel

interface CacheUserUseCase {
    suspend operator fun invoke(user: UserDomainModel)
}