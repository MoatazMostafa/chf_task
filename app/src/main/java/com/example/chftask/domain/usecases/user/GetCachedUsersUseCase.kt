package com.example.chftask.domain.usecases.user

import com.example.chftask.domain.model.UserDomainModel

interface GetCachedUsersUseCase {
    suspend operator fun invoke(): List<UserDomainModel>
}