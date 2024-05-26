package com.iau.afinal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.iau.afinal.data.HotelR
import com.iau.afinal.data.ItemsRepository
import com.iau.afinal.pages.FavViewModel
import com.iau.afinal.pages.HotelsViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {
    /*val favoriteHotels: StateFlow<List<HotelR>> = itemsRepository.getAllItemsStream()
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
    }*/
}

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            val container = HotelApp().container
            FavViewModel(container.itemsRepository)
        }
        initializer {
            val container = HotelApp().container
            HotelsViewModel(container.itemsRepository)
        }
    }
}


fun CreationExtras.HotelApp(): HotelBrowser =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as HotelBrowser)

enum class Pages{
    home,
    hotels,
    fav,
    hotel
}
