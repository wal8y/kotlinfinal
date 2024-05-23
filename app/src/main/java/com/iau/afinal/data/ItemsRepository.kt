package com.iau.afinal.data

import kotlinx.coroutines.flow.Flow

interface ItemsRepository {

    fun getAllItemsStream(): Flow<List<HotelR>>

    fun getItemStream(id: Int): Flow<HotelR?>

    suspend fun insertItem(item: HotelR)

    suspend fun deleteItem(item: HotelR)

    suspend fun updateItem(item: HotelR)
}
