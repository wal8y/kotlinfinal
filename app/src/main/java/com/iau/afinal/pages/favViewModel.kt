package com.iau.afinal.pages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iau.afinal.data.HotelR
import com.iau.afinal.data.ItemsRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class FavViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {
    val favoriteHotels: StateFlow<List<HotelR>> = itemsRepository.getAllItemsStream()
        .stateIn(viewModelScope, kotlinx.coroutines.flow.SharingStarted.Lazily, emptyList())

    fun addHotelToFavorites(hotel: HotelR) {
        viewModelScope.launch {
            itemsRepository.insertItem(hotel)
        }
    }

    fun deleteHotelFromFavorites(hotel: HotelR) {
        viewModelScope.launch {
            itemsRepository.deleteItem(hotel)
        }
    }
}
