package com.example.chftask.data.datasources.remote.venues

import com.example.chftask.BuildConfig
import com.example.chftask.data.datasources.remote.venues.model.VenuesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface VenuesService {
    @GET("venues/search")
    suspend fun getVenues(
        @Query("ll") latLong: String,
        @Query("client_id") clientId: String = BuildConfig.CLIENT_ID,
        @Query("client_secret") clientSecret: String = BuildConfig.CLIENT_SECRET,
        @Query("v") version: String = "20180910"
    ): VenuesResponse
}