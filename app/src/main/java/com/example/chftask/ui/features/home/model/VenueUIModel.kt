package com.example.chftask.ui.features.home.model

import com.example.chftask.domain.model.VenueDomainModel

data class VenueUIModel(
    val id: String?,
    val name: String?,
    val location: LocationUIModel?,
    val categories: List<CategoryUIModel?>?,
    val createdAt: Int?
) {
    data class LocationUIModel(
        val address: String?,
        val lat: Double?,
        val lng: Double?,
        val labeledLatLngs: List<LabeledLatLngUIModel?>?,
        val distance: Int?,
        val postalCode: String?,
        val cc: String?,
        val city: String?,
        val state: String?,
        val country: String?,
        val formattedAddress: List<String?>?,
        val crossStreet: String?
    ) {
        data class LabeledLatLngUIModel(
            val label: String?,
            val lat: Double?,
            val lng: Double?
        )
    }

    data class CategoryUIModel(
        val id: String?,
        val name: String?,
        val pluralName: String?,
        val shortName: String?,
        val icon: IconUIModel?,
        val categoryCode: Int?,
        val mapIcon: String?,
        val primary: Boolean?
    ) {
        data class IconUIModel(
            val prefix: String?,
            val suffix: String?
        )
    }
}

fun VenueDomainModel.toUIModel(): VenueUIModel {
    return VenueUIModel(
        id = id,
        name = name,
        location = location?.toUIModel(),
        categories = categories?.map { it?.toUIModel() },
        createdAt = createdAt
    )
}

fun VenueDomainModel.LocationDomainModel.toUIModel(): VenueUIModel.LocationUIModel {
    return VenueUIModel.LocationUIModel(
        address = address,
        lat = lat,
        lng = lng,
        labeledLatLngs = labeledLatLngs?.map { it?.toUIModel() },
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

fun VenueDomainModel.CategoryDomainModel.toUIModel(): VenueUIModel.CategoryUIModel {
    return VenueUIModel.CategoryUIModel(
        id = id,
        name = name,
        pluralName = pluralName,
        shortName = shortName,
        icon = icon?.toUIModel(),
        categoryCode = categoryCode,
        mapIcon = mapIcon,
        primary = primary
    )
}

fun VenueDomainModel.LocationDomainModel.LabeledLatLngDomainModel.toUIModel():
        VenueUIModel.LocationUIModel.LabeledLatLngUIModel {
    return VenueUIModel.LocationUIModel.LabeledLatLngUIModel(
        label = label,
        lat = lat,
        lng = lng
    )
}

fun VenueDomainModel.CategoryDomainModel.IconDomainModel.toUIModel():
        VenueUIModel.CategoryUIModel.IconUIModel {
    return VenueUIModel.CategoryUIModel.IconUIModel(
        prefix = prefix,
        suffix = suffix
    )
}