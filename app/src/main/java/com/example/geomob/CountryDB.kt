package com.example.geomob

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.geomob.models.Country

@Database(entities = [Country::class], version = 1)
@TypeConverters(Converters::class)
abstract class CountryDB : RoomDatabase() {
    abstract fun countryDAO(): CountryDAO

    companion object {
        var TEST_MODE = false
        private var countryDB: CountryDB? = null
        private var countryDAO: CountryDAO? = null


        fun getInstance(context: Context): CountryDB? {
            if (countryDB == null) {

                countryDB = if (TEST_MODE) {
                    Room.inMemoryDatabaseBuilder(context, CountryDB::class.java).allowMainThreadQueries().build()
                } else {
                    Room.databaseBuilder(context.applicationContext,
                        CountryDB::class.java, "country.db")
                        .build()
                }

            }
            countryDAO = countryDB?.countryDAO()
            return countryDB
        }

        fun destroyInstance() {
            countryDB = null
        }
    }
}