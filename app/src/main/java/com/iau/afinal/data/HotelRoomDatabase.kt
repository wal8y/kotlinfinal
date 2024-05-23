package com.iau.afinal.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.TypeConverters

@Database(entities = [HotelR::class], version = 1, exportSchema = false)
abstract class HotelRoomDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var Instance: HotelRoomDatabase? = null

        fun getDatabase(context: Context): HotelRoomDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, HotelRoomDatabase::class.java, "database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}