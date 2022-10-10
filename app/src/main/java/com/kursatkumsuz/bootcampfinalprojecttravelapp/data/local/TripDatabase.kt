package com.kursatkumsuz.bootcampfinalprojecttravelapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TripEntity

@Database(entities = [TripEntity::class], version = 2)
abstract class TripDatabase : RoomDatabase() {

    /**
     * Accesses TripDao
     * @return [TripDao]
     */
    abstract fun tripDao(): TripDao
}