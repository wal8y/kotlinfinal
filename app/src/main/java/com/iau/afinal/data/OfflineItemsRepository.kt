package com.iau.afinal.data

import kotlinx.coroutines.flow.Flow

class OfflineItemsRepository(private val itemDao: ItemDao) : ItemsRepository {
    override fun getAllItemsStream(): Flow<List<HotelR>> = itemDao.getAllItems()

    override fun getItemStream(id: Int): Flow<HotelR?> = itemDao.getItem(id)

    override suspend fun insertItem(item: HotelR) = itemDao.insert(item)

    override suspend fun deleteItem(item: HotelR) = itemDao.delete(item)

    override suspend fun updateItem(item: HotelR) = itemDao.update(item)
}
