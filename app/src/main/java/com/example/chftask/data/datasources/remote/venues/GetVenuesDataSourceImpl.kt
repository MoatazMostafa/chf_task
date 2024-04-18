package com.example.chftask.data.datasources.remote.venues

import com.example.chftask.common.network.ServerRequest
import com.example.chftask.common.network.configuration.ConfigurationService
import com.example.chftask.common.util.Response
import com.example.chftask.data.datasources.remote.venues.model.VenuesResponse

class GetVenuesDataSourceImpl(configService: ConfigurationService) :
    ServerRequest(configService.baseUrl), GetVenuesDataSource {
    private val backendService by lazy { retrofit.create(VenuesService::class.java) }
    override suspend fun invoke(latLong: String): Response<List<VenuesResponse.Response.Venue>> {
        return try {
            if (backendService.getVenues(latLong).meta?.code == 200) {
                Response.Success(backendService.getVenues(latLong).response?.venues)
            } else {
                Response.Error(
                    "Server Error",
                    backendService.getVenues(latLong).meta?.code.toString()
                )
            }
        } catch (e: Exception) {
            Response.Error(e.message ?: "An error occurred")
        }
    }

}