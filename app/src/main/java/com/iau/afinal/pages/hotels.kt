package com.iau.afinal.pages

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.room.PrimaryKey
import com.iau.afinal.MainViewModel
import com.iau.afinal.Pages
import com.iau.afinal.data.HotelR
import com.iau.afinal.data.hotels


data class Hotel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @DrawableRes
    val imageResource: Int,
    val Name: String,
    val bio: String,
    val location: String,
    val stars: Int
)

@Composable
fun HotelsPage(navController: NavHostController, viewModel: HotelsViewModel) {
    val hotels = remember { mutableStateOf(hotels) }
    val sortedBy = remember { mutableStateOf("") }

    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FilterOptions(onSortBySelected = { sortBy ->
                sortedBy.value = sortBy
                hotels.value = when (sortBy) {
                    "A-Z" -> hotels.value.sortedBy { it.Name }
                    "Stars" -> hotels.value.sortedByDescending { it.stars }
                    else -> hotels.value
                }
            })
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(hotels.value) { hotel ->
                    HotelCard(hotel, navController)
                }
            }
            NavBar(navController = navController, viewModel = viewModel)
        }
    }
}

@Composable
fun FilterOptions(onSortBySelected: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Sort By:",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(end = 8.dp)
        )
        FilterOption(
            text = "A-Z",
            onSelected = { onSortBySelected("A-Z") }
        )
        FilterOption(
            text = "Stars",
            onSelected = { onSortBySelected("Stars") }
        )
    }
}

@Composable
fun FilterOption(text: String, onSelected: () -> Unit) {
    Surface(
        color = Color(0,115,205),
        tonalElevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.clickable(onClick = onSelected)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}


@Composable
fun HotelCard(hotel: Hotel, navController: NavHostController) {
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
                navController.navigate("${Pages.hotel.name}/${hotel.Name}")
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
            Text(text = hotel.Name, style = MaterialTheme.typography.titleMedium)
            Text(text = hotel.bio, style = MaterialTheme.typography.bodyMedium)
            Stars(num = hotel.stars, 12)
        }
        Image(
            painter = painterResource(hotel.imageResource),
            contentDescription = hotel.Name,
            modifier = Modifier.size(80.dp).clip(RoundedCornerShape(22.dp))
        )
    }
}
}


@Composable
fun Stars(num: Int,size:Int) {
    Row {
        repeat(5) { index ->
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = if (index < num) "Filled star" else "Empty star",
                tint = if (index < num) Color.Yellow else Color.DarkGray,
                modifier = Modifier.size(size.dp)
            )
        }
    }
}
