package com.example.chftask.ui.features.home.model

enum class HomeTab(val tabText:String) {
    Venues("Venues"),
    Map("Map");

    companion object {
        fun fromString(tabText: String): HomeTab {
            return when (tabText) {
                "Venues" -> Venues
                "Map" -> Map
                else -> throw IllegalArgumentException("Invalid tab text")
            }
        }
    }
}