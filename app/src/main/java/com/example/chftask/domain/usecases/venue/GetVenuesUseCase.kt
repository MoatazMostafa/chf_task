package com.example.chftask.domain.usecases.venue

import com.example.chftask.common.util.Response
import com.example.chftask.domain.model.VenueDomainModel

interface GetVenuesUseCase {
    suspend operator fun invoke(latLong: String): Response<List<VenueDomainModel>>
}