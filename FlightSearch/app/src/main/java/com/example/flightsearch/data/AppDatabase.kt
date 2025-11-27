package com.example.flightsearch.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

//originally version is 1
@Database(entities = [FlightSearch::class,Favorite::class], version = 2)
//run once to createfromasset and then change version to 1
abstract class AppDatabase: RoomDatabase() {
    abstract fun flightSearchDao(): FlightSearchDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // No schema change needed — keep existing data.
                // This migration prevents Room from deleting the DB.
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .createFromAsset("database/flight_search.db")
                    //do so for the start , then after its loaded
                    // This line loads a prebuilt database from assets, which replaces user data and may wipe the favorites table after reinstall or schema change.

                    //.addMigrations(MIGRATION_1_2) // Prevents destructive recreation
                    //after populating db , remove the create from asset line and then uncomment the migration line

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
