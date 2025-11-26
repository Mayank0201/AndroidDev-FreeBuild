package com.example.flightsearch.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FlightSearch::class,Favorite::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun flightSearchDao(): FlightSearchDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    //.createFromAsset("database/flight_search.db")
                    //do so for the start , then after its loaded , no need for this line
                    // This line loads a prebuilt database from assets, which replaces user data and may wipe the favorites table after reinstall or schema change.
                    .fallbackToDestructiveMigration()
                    // If the DB schema changes and no migration is provided,
// this will delete the existing DB and recreate it (data loss).
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }
        }
    }
}
