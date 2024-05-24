package com.iau.afinal.pages

import android.location.Location
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.iau.afinal.MyViewModel
import com.iau.afinal.Pages
import com.iau.afinal.data.HotelR

@Composable
fun hotelPage(
    Name: String,
    imageResource: Int,
    bio: String,
    location: String,
    stars: Int,
    navController: NavHostController,
    viewModel: MyViewModel
) {
    val context = LocalContext.current
    val favoriteHotelsState = viewModel.favoriteHotels.collectAsState().value

    val favoriteHotel = HotelR(
        name = Name,
        image = imageResource,
        bio = bio,
        location = location,
        stars = stars
    )

    val isFavorite = favoriteHotelsState.any { it == favoriteHotel }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = Name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .border(
                    width = 10.dp,
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0, 115, 205)
                ),
            alignment = Alignment.TopCenter
        )
        Text(
            text = Name,
            fontSize = 30.sp,
            modifier = Modifier.padding(20.dp)
        )
        Stars(num = stars, 30)
        Text(bio, modifier = Modifier.padding(20.dp))
        LocationWebView(location = location)

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Button(
                    onClick = {
                        navController.navigate(Pages.hotels.name)
                    }
                ) {
                    Text("Back")
                }
                Button(
                    onClick = {
                        if (isFavorite) {
                            Toast.makeText(
                                context,
                                "This hotel is already in favorites",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            viewModel.addHotelToFavorites(favoriteHotel)
                            navController.popBackStack()
                            Toast.makeText(
                                context,
                                "Hotel ${favoriteHotel.name} is added to favorites",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                ) {
                    Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "fav")
                }
            }
        }
    }
}

@Composable
fun LocationWebView(location: String) {
    val l = toLocation(location)
    val hotel = LatLng(l.latitude, l.longitude)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(hotel, 14f)
    }
    GoogleMap(
        modifier = Modifier.size(200.dp),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = hotel),
            title = "hotel location",
            snippet = "Marker"
        )
    }
}



fun toLocation(locationString: String): Location {
    val parts = locationString.split(",")
    val latitude = parts[0].toDouble()
    val longitude = parts[1].toDouble()
    return Location("").apply {
        this.latitude = latitude
        this.longitude = longitude
    }
}