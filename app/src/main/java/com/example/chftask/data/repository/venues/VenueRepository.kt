package com.example.chftask.data.repository.venues

import com.example.chftask.common.util.Response
import com.example.chftask.domain.model.VenueDomainModel

interface VenueRepository {
    suspend fun getVenues(latLong: String): Response<List<VenueDomainModel>>
}