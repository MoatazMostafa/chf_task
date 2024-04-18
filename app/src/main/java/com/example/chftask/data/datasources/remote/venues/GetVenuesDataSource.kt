package com.example.chftask.data.datasources.remote.venues

import com.example.chftask.common.util.Response
import com.example.chftask.data.datasources.remote.venues.model.VenuesResponse

interface GetVenuesDataSource {
    suspend operator fun invoke(latLong: String): Response<List<VenuesResponse.Response.Venue>>
}