package com.example.chftask.data.datasources.remote.venues.model


import com.google.gson.annotations.SerializedName

data class VenuesResponse(
    @SerializedName("meta") val meta: Meta?,
    @SerializedName("response") val response: Response?
) {
    data class Meta(
        @SerializedName("code") val code: Int?,
        @SerializedName("requestId") val requestId: String?
    )

    data class Response(
        @SerializedName("venues") val venues: List<Venue>?,
        @SerializedName("confident") val confident: Boolean?
    ) {
        data class Venue(
            @SerializedName("id") val id: String?,
            @SerializedName("name") val name: String?,
            @SerializedName("location") val location: Location?,
            @SerializedName("categories") val categories: List<Category?>?,
            @SerializedName("createdAt") val createdAt: Int?
        ) {
            data class Location(
                @SerializedName("address") val address: String?,
                @SerializedName("lat") val lat: Double?,
                @SerializedName("lng") val lng: Double?,
                @SerializedName("labeledLatLngs") val labeledLatLngs: List<LabeledLatLng?>?,
                @SerializedName("distance") val distance: Int?,
                @SerializedName("postalCode") val postalCode: String?,
                @SerializedName("cc") val cc: String?,
                @SerializedName("city") val city: String?,
                @SerializedName("state") val state: String?,
                @SerializedName("country") val country: String?,
                @SerializedName("formattedAddress") val formattedAddress: List<String?>?,
                @SerializedName("crossStreet") val crossStreet: String?
            ) {
                data class LabeledLatLng(
                    @SerializedName("label") val label: String?,
                    @SerializedName("lat") val lat: Double?,
                    @SerializedName("lng") val lng: Double?
                )
            }

            data class Category(
                @SerializedName("id") val id: String?,
                @SerializedName("name") val name: String?,
                @SerializedName("pluralName") val pluralName: String?,
                @SerializedName("shortName") val shortName: String?,
                @SerializedName("icon") val icon: Icon?,
                @SerializedName("categoryCode") val categoryCode: Int?,
                @SerializedName("mapIcon") val mapIcon: String?,
                @SerializedName("primary") val primary: Boolean?
            ) {
                data class Icon(
                    @SerializedName("prefix") val prefix: String?,
                    @SerializedName("suffix") val suffix: String?
                )
            }
        }
    }
}