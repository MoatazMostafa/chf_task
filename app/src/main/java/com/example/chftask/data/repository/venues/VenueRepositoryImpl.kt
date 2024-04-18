package com.example.chftask.data.repository.venues

import com.example.chftask.common.util.Response
import com.example.chftask.data.datasources.remote.venues.GetVenuesDataSource
import com.example.chftask.domain.model.VenueDomainModel
import com.example.chftask.domain.model.toDomainModel

class VenueRepositoryImpl(val getVenuesDataSource: GetVenuesDataSource) : VenueRepository {
    override suspend fun getVenues(latLong: String): Response<List<VenueDomainModel>> {
        return  when (val response = getVenuesDataSource(latLong)) {
            is Response.Success -> Response.Success(response.data?.map { it.toDomainModel() })
            is Response.Error -> Response.Error(response.errorCode?:"", response.errorMessage?:"")
        }
    }
}