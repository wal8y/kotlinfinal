package com.iau.afinal.data

import android.location.Location
import android.media.Image
import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class HotelR(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @DrawableRes
    val image: Int,
    val name: String,
    val bio: String,
    val location: String,
    val stars: Int
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is HotelR) return false
        if (image != other.image) return false
        if (name != other.name) return false
        if (bio != other.bio) return false
        if (location != other.location) return false
        if (stars != other.stars) return false

        return true
    }
}