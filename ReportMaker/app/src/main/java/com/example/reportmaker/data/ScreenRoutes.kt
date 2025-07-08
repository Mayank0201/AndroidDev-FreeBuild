package com.example.reportmaker.data

sealed class ScreenRoutes(val route_name: String) {
    object Intro : ScreenRoutes("intro")
    object Marks1 : ScreenRoutes("marks1")
    object Marks2 : ScreenRoutes("marks2")
    object Final : ScreenRoutes("final")
}