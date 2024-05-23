package com.iau.afinal.pages


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.iau.afinal.MyViewModel
import com.iau.afinal.Pages
import com.iau.afinal.data.HotelR

@Composable
fun FavPage(navController: NavHostController, viewModel: MyViewModel) {
    viewModel.currentpage == 3
    
    Scaffold (
        topBar = {

        },
        bottomBar = {
            NavBar(navController = navController, viewModel = viewModel)
        }
    ){pad->
        HotelsList(navController = navController, viewModel = viewModel,pad)
    }
}

@Composable
fun HotelsList(navController: NavHostController, viewModel: MyViewModel,pad: PaddingValues) {
    val hotels by viewModel.favoriteHotels.collectAsState()
    LazyColumn {
        items(hotels) { hotel ->
            HotelItem(hotel = hotel, navController = navController,viewModel)
        }
    }
}

@Composable
fun HotelItem(hotel: HotelR, navController: NavHostController,viewModel: MyViewModel) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("fav${Pages.hotel.name}/${hotel.name}")
            }
            .padding(15.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0, 115, 205),
                        Color(0, 155, 205)
                    )
                ), RoundedCornerShape(20.dp)
            )
    ){
        Column {
            Text(text = hotel.name, style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(8.dp))
            Stars(num = hotel.stars, size = 12)
            Text(text = hotel.bio, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(6.dp))
            Button(onClick = {
                viewModel.deleteHotelFromFavorites(hotel = hotel)
            }) {
                Text(text = "Remove")
            }
        }
        Image(painter = painterResource(id = hotel.image), contentDescription = "",
            modifier = Modifier.size(80.dp).clip(RoundedCornerShape(22.dp)))
    }
}