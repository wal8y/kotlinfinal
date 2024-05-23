package com.iau.afinal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.iau.afinal.data.HotelR
import com.iau.afinal.MainActivity
import com.iau.afinal.data.ItemsRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MyViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {
    var currentpage by mutableStateOf(0)

    val favoriteHotels: StateFlow<List<HotelR>> = itemsRepository.getAllItemsStream()
        .stateIn(viewModelScope, kotlinx.coroutines.flow.SharingStarted.Lazily, emptyList())

    fun addHotelToFavorites(hotel: HotelR) {
        viewModelScope.launch {
            itemsRepository.insertItem(hotel)
        }
    }
}

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            MyViewModel(inventoryApplication().container.itemsRepository)
        }
    }
}

fun CreationExtras.inventoryApplication(): HotelBrowser =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as HotelBrowser)

enum class Pages{
    home,
    hotels,
    user,
    fav,
    hotel
}
