package com.example.flightsearch.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearch.FlightSearchApplication
import com.example.flightsearch.data.Favorite
import com.example.flightsearch.data.FlightSearch
import com.example.flightsearch.data.FlightSearchDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FlightSearchViewModel(private val flightSearchDao: FlightSearchDao): ViewModel() {

    val favorites = flightSearchDao.getAll().stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun searchFlights(q: String): Flow<List<FlightSearch>> = flightSearchDao.getItems(q)

    fun getDestinations(excludedCode: String): Flow<List<FlightSearch>> = flightSearchDao.getDestinations(excludedCode)

    fun toggleFavorite(departure: String, destination: String) {
        viewModelScope.launch {
            //Log.d("FlightVM", "Toggling Favorite: $departure → $destination")
            if (flightSearchDao.exists(departure,destination)) {
                flightSearchDao.deleteFavorite(departure,destination)
                //Log.d("FlightVM", "Favorite removed")
            } else {
                val favorite = Favorite(departureCode = departure, destinationCode = destination)
                flightSearchDao.insertFavorite(favorite)
                //Log.d("FlightVM", "Favorite inserted: $favorite")
            }
        }
    }

    fun removeFavorite(favorite: Favorite) {
        viewModelScope.launch {
            flightSearchDao.deleteFavorite(favorite)
        }
    }

    fun getFavoriteDestinationName():Flow<List<FlightSearch>> = flightSearchDao.getFavoriteDestinationAirportNames()
    fun getFavoriteDepartureName():Flow<List<FlightSearch>> = flightSearchDao.getFavoriteDepartureAirportNames()

    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FlightSearchApplication)
                FlightSearchViewModel(application.database.flightSearchDao())
            }
        }
    }
}