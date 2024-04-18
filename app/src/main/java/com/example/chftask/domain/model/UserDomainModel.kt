package com.example.chftask.domain.model

import com.example.chftask.data.datasources.local.user.model.User

data class UserDomainModel(
    val id: String,
    val firstName: String,
    val lastName: String,
    val age: String,
    val email: String,
    val password: String
)

fun UserDomainModel.toDataModel() = User(
    id = id,
    firstName = firstName,
    lastName = lastName,
    age = age,
    email = email,
    password = password
)

fun User.toDomainModel() = UserDomainModel(
    id = id,
    firstName = firstName,
    lastName = lastName,
    age = age,
    email = email,
    password = password
)