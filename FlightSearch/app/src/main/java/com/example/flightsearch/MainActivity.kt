package com.example.flightsearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.data.AppDatabase
import com.example.flightsearch.data.FlightSearchDao
import com.example.flightsearch.ui.FlightSearchScreen
import com.example.flightsearch.ui.FlightSearchViewModel
import com.example.flightsearch.ui.theme.FlightSearchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlightSearchTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel: FlightSearchViewModel = viewModel(
                        factory = FlightSearchViewModel.factory
                    )
                    FlightSearchScreen(modifier=Modifier.padding(innerPadding),viewModel=viewModel)
                }
            }
        }
    }
}



