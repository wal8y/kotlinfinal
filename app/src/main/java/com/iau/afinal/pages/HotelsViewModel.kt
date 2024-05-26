package com.iau.afinal.pages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iau.afinal.data.HotelR
import com.iau.afinal.data.ItemsRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class HotelsViewModel(private val hotelsRepository: ItemsRepository) : ViewModel() {
    /*val allHotels: StateFlow<List<HotelR>> = hotelsRepository.getAllItemsStream()
        .stateIn(viewModelScope, kotlinx.coroutines.flow.SharingStarted.Lazily, emptyList())*/
}
