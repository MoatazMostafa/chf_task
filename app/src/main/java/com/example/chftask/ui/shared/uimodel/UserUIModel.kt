package com.example.chftask.ui.shared.uimodel

import com.example.chftask.domain.model.UserDomainModel

data class UserUIModel(
    val id: String,
    val firstName: String,
    val lastName: String,
    val age: String,
    val email: String,
    val password: String
)

fun UserUIModel.toDomainModel() = UserDomainModel(
    id = id,
    firstName = firstName,
    lastName = lastName,
    age = age,
    email = email,
    password = password
)

fun UserDomainModel.toUIModel() = UserUIModel(
    id = id,
    firstName = firstName,
    lastName = lastName,
    age = age,
    email = email,
    password = password
)

fun UserUIModel.get() = UserDomainModel(
    id = "",
    firstName = "",
    lastName = "",
    age = "",
    email = "",
    password = ""
)