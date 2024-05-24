package com.iau.afinal.pages


import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.iau.afinal.MyViewModel
import com.iau.afinal.Pages
import com.iau.afinal.data.HotelR

@Composable
fun FavPage(navController: NavHostController, viewModel: MyViewModel) {
    
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
    if (hotels.isEmpty()) {
        Text(
            text = "\nNo Hotels added to Favorites Yet..",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.padding(pad),
        )
    } else {
        LazyColumn {
            items(hotels) { hotel ->
                HotelItem(hotel = hotel, navController = navController,viewModel)
            }
        }
    }
}

@Composable
fun HotelItem(hotel: HotelR, navController: NavHostController,viewModel: MyViewModel) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .padding(7.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0,115,205),
                        Color(0,155,205)
                    )
                ),
                shape = RoundedCornerShape(14.dp)
            )
            .border(
                border = BorderStroke(4.dp, Color.Unspecified),
                shape = RoundedCornerShape(14.dp)
            )
            .fillMaxWidth()
            .clickable {
                navController.navigate("${Pages.hotel.name}/${hotel.name}")
            }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = hotel.name,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(8.dp)
                )
                Stars(num = hotel.stars, size = 12)
                Text(
                    text = hotel.bio,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(6.dp)
                )
                Button(onClick = {
                    viewModel.deleteHotelFromFavorites(hotel = hotel)
                    Toast.makeText(
                        context,
                        "Hotel ${hotel.name} Has been removed",
                        Toast.LENGTH_SHORT
                    ).show()
                }) {
                    Text(text = "Remove")
                }
            }
            Image(
                painter = painterResource(hotel.image),
                contentDescription = hotel.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(120.dp)
                    .border(color = Color.White, shape = RoundedCornerShape(3.dp), width = 5.dp)
                    .background(Color.Transparent)
            )
        }
    }
}