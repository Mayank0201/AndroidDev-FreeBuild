package com.example.flightsearch.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import com.example.flightsearch.data.Favorite
import com.example.flightsearch.data.FlightSearch
import kotlinx.coroutines.delay
import androidx.compose.material.icons.outlined.Star


//ADD TOP APP BAR
@Composable
fun FlightSearchScreen(modifier:Modifier=Modifier,viewModel: FlightSearchViewModel) {

    var search by remember { mutableStateOf("") }

    val favoriteList: List<Favorite> by viewModel.favorites.collectAsState()

    var selectedAirport by remember { mutableStateOf<FlightSearch?>(null) }

    val destinationList by viewModel.getDestinations(selectedAirport?.iataCode ?: "")
        .collectAsState(initial = emptyList())

    var debouncedSearch by remember { mutableStateOf("") }

    val flightList by viewModel.searchFlights(debouncedSearch).collectAsState(initial = emptyList())

    val favoriteDestination by viewModel.getFavoriteDestinationName()
        .collectAsState(initial = emptyList())

    val favoriteDeparture by viewModel.getFavoriteDepartureName()
        .collectAsState(initial = emptyList())


    LaunchedEffect(search) {
        delay(300)
        debouncedSearch = search
    }

    Column(modifier=modifier.fillMaxSize()){
    FlightSearchField(search = search, onValueChange = { search = it })
    if(search!=""){
        Text(text = "Flights from $search:")
    }
    else{
        Text(text="Favorite Routes:")
    }
    Spacer(modifier = Modifier.height(8.dp))

        if (selectedAirport != null) {
            TextButton(
                onClick = {
                    selectedAirport = null
                    search = ""
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("<- Back")
            }
        }
        LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            when {
                search.isBlank() -> {

                    items(favoriteList) { favorite ->
                        FavoriteCard(favorite = favorite, flightStart = favoriteDeparture,
                            flightEnd=favoriteDestination,onRemoveFavorite = {
                                viewModel.removeFavorite(it) })
                    }
                }
                selectedAirport == null -> {
                    items(flightList) { flight ->
                        FlightCard(flight = flight, onClick = {
                            selectedAirport = flight
                            search = flight.name
                        })
                    }
                }
                else -> {
                    val filteredDestinations = destinationList
                    item {
                        DestinationCardList(
                            selectedAirport = selectedAirport!!,
                            destinationList = filteredDestinations,
                            favoriteList = favoriteList,
                            onToggleFavorite = { dep, dest ->
                                viewModel.toggleFavorite(dep, dest)
                            })
                    }
                }
            }
        }
    }
}

@Composable
fun FlightSearchField(
    modifier: Modifier = Modifier,
    search: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = search,
        onValueChange = onValueChange,
        label = { Text("Search flights...") },
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun FlightCard(flight: FlightSearch,onClick: (FlightSearch) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp).
            clickable { onClick(flight) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "ID: ${flight.id}", style = MaterialTheme.typography.labelMedium)
            Text(text = "Name: ${flight.name}", style = MaterialTheme.typography.titleMedium)
            Text(text = "IATA Code: ${flight.iataCode}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Passengers: ${flight.passengers}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun FavoriteCard(
    modifier: Modifier = Modifier,
    favorite: Favorite,
    flightStart: List<FlightSearch>,
    flightEnd: List<FlightSearch>,
    onRemoveFavorite: (Favorite) -> Unit
) {

    val departureNames = flightStart
        .filter { it.iataCode == favorite.departureCode }
        .joinToString { it.name }

    val arrivalNames = flightEnd
        .filter { it.iataCode == favorite.destinationCode }
        .joinToString { it.name }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Depart:\n${favorite.departureCode} ($departureNames)",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Arrive:\n${favorite.destinationCode} ($arrivalNames)",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            IconButton(onClick = { onRemoveFavorite(favorite) }) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Remove from Favorites",
                    tint = Color.Yellow
                )
            }
        }
    }
}

@Composable
fun DestinationCard(
    fromAirport: FlightSearch,
    toAirport: FlightSearch,
    isFavorite: Boolean,
    onStarClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("From: ${fromAirport.name}", style = MaterialTheme.typography.titleMedium)
                Text("IATA: ${fromAirport.iataCode}", style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(4.dp))
                Text("To: ${toAirport.name}", style = MaterialTheme.typography.titleMedium)
                Text("IATA: ${toAirport.iataCode}", style = MaterialTheme.typography.bodySmall)
            }

            IconButton(
                modifier = Modifier.size(40.dp),
                onClick = onStarClick
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Star else Icons.Outlined.Star,
                    contentDescription = "Favorite",
                    tint = if (isFavorite) Color.Yellow else Color.Gray
                )
            }
        }
    }
}



@Composable
fun DestinationCardList(
    selectedAirport: FlightSearch,
    destinationList: List<FlightSearch>,
    favoriteList: List<Favorite>,
    onToggleFavorite: (String, String) -> Unit
) {
    Column {
        destinationList.forEach { destination ->
            val isFavorite = favoriteList.any {
                it.departureCode == selectedAirport.iataCode &&
                        it.destinationCode == destination.iataCode
            }

            DestinationCard(
                fromAirport = selectedAirport,
                toAirport = destination,
                isFavorite = isFavorite,
                onStarClick = { onToggleFavorite(selectedAirport.iataCode, destination.iataCode) }
            )
        }
    }
}

