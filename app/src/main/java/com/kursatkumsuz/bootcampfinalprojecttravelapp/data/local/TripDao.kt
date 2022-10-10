package com.kursatkumsuz.bootcampfinalprojecttravelapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TripEntity

@Dao
interface TripDao {

    /**
     * Inserts trip into room database
     * @param [trip] for insert into room
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrip(trip : TripEntity)

    /**
     * Deletes trip from room database
     * @param [trip] for delete from room
     */
    @Delete
    suspend fun deleteTrip(trip: TripEntity)

    /**
     * Get all data from room database
     * @return [List] that contains TripEntity
     */
    @Query("SELECT * FROM trips")
    suspend fun getTrips() : List<TripEntity>


}