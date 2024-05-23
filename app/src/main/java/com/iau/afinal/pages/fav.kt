package com.iau.afinal.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.iau.afinal.MyViewModel
import com.iau.afinal.data.HotelR

@Composable
fun FavPage(navController: NavHostController, viewModel: MyViewModel) {
    viewModel.currentpage == 3

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Text(text = "Favorite Hotels", modifier = Modifier.padding(16.dp))
            HotelsList(navController,viewModel = viewModel)
        }
    }
}

@Composable
fun HotelsList(navController: NavHostController, viewModel: MyViewModel) {
    val hotels by viewModel.favoriteHotels.collectAsState()

    LazyColumn {
        items(hotels) { hotel ->
            HotelItem(hotel = hotel, navController = navController)
        }
    }
}

@Composable
fun HotelItem(hotel: HotelR, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                // Handle item click, maybe navigate to the hotel detail page
            }
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(hotel.image),
            contentDescription = hotel.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
        Text(text = hotel.name, style = MaterialTheme.typography.titleMedium)
        Text(text = hotel.bio, style = MaterialTheme.typography.bodyMedium)
    }
}