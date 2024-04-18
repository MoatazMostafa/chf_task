package com.example.chftask.domain.model

import com.example.chftask.data.datasources.remote.venues.model.VenuesResponse

data class VenueDomainModel(
    val id: String?,
    val name: String?,
    val location: LocationDomainModel?,
    val categories: List<CategoryDomainModel?>?,
    val createdAt: Int?
) {
    data class LocationDomainModel(
        val address: String?,
        val lat: Double?,
        val lng: Double?,
        val labeledLatLngs: List<LabeledLatLngDomainModel?>?,
        val distance: Int?,
        val postalCode: String?,
        val cc: String?,
        val city: String?,
        val state: String?,
        val country: String?,
        val formattedAddress: List<String?>?,
        val crossStreet: String?
    ) {
        data class LabeledLatLngDomainModel(
            val label: String?,
            val lat: Double?,
            val lng: Double?
        )
    }

    data class CategoryDomainModel(
        val id: String?,
        val name: String?,
        val pluralName: String?,
        val shortName: String?,
        val icon: IconDomainModel?,
        val categoryCode: Int?,
        val mapIcon: String?,
        val primary: Boolean?
    ) {
        data class IconDomainModel(
            val prefix: String?,
            val suffix: String?
        )
    }
}

fun VenuesResponse.Response.Venue.toDomainModel(): VenueDomainModel {
    return VenueDomainModel(
        id = id,
        name = name,
        location = location?.toDomainModel(),
        categories = categories?.map { it?.toDomainModel() },
        createdAt = createdAt
    )
}

fun VenuesResponse.Response.Venue.Location.toDomainModel(): VenueDomainModel.LocationDomainModel {
    return VenueDomainModel.LocationDomainModel(
        address = address,
        lat = lat,
        lng = lng,
        labeledLatLngs = labeledLatLngs?.map { it?.toDomainModel() },
        distance = distance,
        postalCode = postalCode,
        cc = cc,
        city = city,
        state = state,
        country = country,
        formattedAddress = formattedAddress,
        crossStreet = crossStreet
    )
}

fun VenuesResponse.Response.Venue.Category.toDomainModel(): VenueDomainModel.CategoryDomainModel {
    return VenueDomainModel.CategoryDomainModel(
        id = id,
        name = name,
        pluralName = pluralName,
        shortName = shortName,
        icon = icon?.toDomainModel(),
        categoryCode = categoryCode,
        mapIcon = mapIcon,
        primary = primary
    )
}

fun VenuesResponse.Response.Venue.Location.LabeledLatLng.toDomainModel():
        VenueDomainModel.LocationDomainModel.LabeledLatLngDomainModel {
    return VenueDomainModel.LocationDomainModel.LabeledLatLngDomainModel(
        label = label,
        lat = lat,
        lng = lng
    )
}
fun VenuesResponse.Response.Venue.Category.Icon.toDomainModel():
        VenueDomainModel.CategoryDomainModel.IconDomainModel {
    return VenueDomainModel.CategoryDomainModel.IconDomainModel(
        prefix = prefix,
        suffix = suffix
    )
}