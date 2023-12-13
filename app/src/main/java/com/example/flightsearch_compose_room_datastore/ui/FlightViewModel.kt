package com.example.flightsearch_compose_room_datastore.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearch_compose_room_datastore.FlightSearchApplication
import com.example.flightsearch_compose_room_datastore.R
import com.example.flightsearch_compose_room_datastore.data.AirportCard
import com.example.flightsearch_compose_room_datastore.data.FlightRepository


data class FlightUiState(
    val searchField : String = "",
    val favoriteRoute : Boolean = false,
    val starIcon : Int = if (favoriteRoute) R.drawable.ic_star_selected else R.drawable.ic_star_notselected,
    val airportCard: AirportCard = AirportCard()

)

class FlightViewModel(private val flightRepository: FlightRepository): ViewModel() {


    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                FlightViewModel(FlightSearchApplication().container.flightRepository)
            }
        }
    }
}