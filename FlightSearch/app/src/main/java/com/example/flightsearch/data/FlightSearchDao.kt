package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightSearchDao {

    @Query("SELECT * FROM favorite ORDER BY id ASC")
    fun getAll(): Flow<List<Favorite>>

    @Query("SELECT * FROM airport WHERE name LIKE '%' || :q || '%' OR iata_code LIKE '%' || :q || '%'")
    fun getItems(q: String): Flow<List<FlightSearch>>

    @Query("SELECT * FROM airport WHERE iata_code != :excludedCode")
    fun getDestinations(excludedCode: String): Flow<List<FlightSearch>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite WHERE departure_code = :from AND destination_code = :to)")
    suspend fun exists(from: String, to: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite): Long

    @Query("DELETE FROM favorite WHERE departure_code = :departureCode AND destination_code = :destinationCode")
    suspend fun deleteFavorite(departureCode: String, destinationCode: String): Int

    @Delete
    suspend fun deleteFavorite(favorite: Favorite):Int

    @Query("SELECT a.* FROM airport a INNER JOIN favorite f ON f.departure_code = a.iata_code ORDER BY f.id ASC")
    fun getFavoriteDepartureAirportNames(): Flow<List<FlightSearch>>

    @Query("SELECT a.* FROM airport a INNER JOIN favorite f ON f.destination_code = a.iata_code ORDER BY f.id ASC")
    fun getFavoriteDestinationAirportNames(): Flow<List<FlightSearch>>

}


