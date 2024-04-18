package com.example.chftask.domain.usecases.user

import com.example.chftask.domain.model.UserDomainModel

interface GetCachedCurrentUserUseCase {
    suspend operator fun invoke(): UserDomainModel?
}