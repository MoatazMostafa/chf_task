package com.example.chftask.domain.usecases.venue

import com.example.chftask.common.util.Response
import com.example.chftask.data.repository.venues.VenueRepository
import com.example.chftask.domain.model.VenueDomainModel

class GetVenuesUseCaseImpl(
    private val repository: VenueRepository
) : GetVenuesUseCase {
    override suspend fun invoke(latLong: String): Response<List<VenueDomainModel>> {
        return repository.getVenues(latLong)
    }
}