package com.example.chftask.ui.features.home

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewModelScope
import com.example.chftask.R
import com.example.chftask.common.util.Response
import com.example.chftask.domain.usecases.venue.GetVenuesUseCase
import com.example.chftask.ui.features.home.model.HomeTab
import com.example.chftask.ui.features.home.model.VenueUIModel
import com.example.chftask.ui.features.home.model.toUIModel
import com.example.chftask.ui.shared.base.BaseViewModel
import com.example.chftask.ui.shared.uimodel.LoadingState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    application: Application,
    val getVenuesUseCase: GetVenuesUseCase
) : BaseViewModel(
    application = application,
) {
    private val _selectedTabIndex = MutableStateFlow(0)
    val selectedTabIndex = _selectedTabIndex.asStateFlow()

    private val _venuesList = MutableStateFlow<List<VenueUIModel>>(emptyList())
    var venuesList = _venuesList.asStateFlow()

    private val fusedLocationClient: FusedLocationProviderClient

    init {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)
        if (!isLocationEnabled()) {
            handleLocalError(
                errorTitle = application.getString(R.string.location_is_disabled),
                errorMessage = application.getString(R.string.please_enable_location_services)
            )
        } else {
            fetchVenuesList()
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager =
            application.applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun fetchVenuesList() {
        viewModelScope.launch {
            loadingState.value = LoadingState.LOADING
            if (ActivityCompat.checkSelfPermission(
                    application,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    application,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                loadingState.value = LoadingState.DONE
                return@launch
            }
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    val latLong = "${it.latitude},${it.longitude}"
                    viewModelScope.launch {
                        when (val response = getVenuesUseCase(latLong)) {
                            is Response.Success -> {
                                loadingState.value = LoadingState.DONE
                                _venuesList.value =
                                    response.data?.map { venues ->
                                        venues.toUIModel()
                                    } ?: emptyList()
                            }

                            is Response.Error -> {
                                loadingState.value = LoadingState.DONE
                                handleServerError(response.errorCode ?: "")
                            }
                        }
                    }
                }
            }
        }
    }

    fun onTabClick(homeTab: HomeTab) {
        _selectedTabIndex.value = HomeTab.entries.indexOf(homeTab)
    }

    fun onPermissionGranted() {
        if (isLocationEnabled()) {
            fetchVenuesList()
        } else {
            handleLocalError(
                errorTitle = application.getString(R.string.location_is_disabled),
                errorMessage = application.getString(R.string.please_enable_location_services)
            )
        }

    }

    fun onPermissionDenied() {
        handleLocalError(
            errorTitle = application.getString(R.string.location_permission_denied),
            errorMessage = application.getString(R.string.please_grant_location_permission_from_app_settings)
        )
    }
}