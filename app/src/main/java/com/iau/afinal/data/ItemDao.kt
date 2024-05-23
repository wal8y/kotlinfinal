package com.iau.afinal.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<HotelR>>

    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<HotelR>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: HotelR)

    @Update
    suspend fun update(item: HotelR)

    @Delete
    suspend fun delete(item: HotelR)
}
