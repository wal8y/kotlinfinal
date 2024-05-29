package com.iau.afinal.pages

import androidx.lifecycle.ViewModel
import com.iau.afinal.data.ItemsRepository

class HotelsViewModel(private val hotelsRepository: ItemsRepository) : ViewModel() {
    /*val allHotels: StateFlow<List<HotelR>> = hotelsRepository.getAllItemsStream()
        .stateIn(viewModelScope, kotlinx.coroutines.flow.SharingStarted.Lazily, emptyList())*/
}
