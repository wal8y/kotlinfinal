package com.iau.afinal.pages

import android.location.Location
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
    location: Location,
    stars: Int,
    navController: NavHostController,
    viewModel: MyViewModel
) {
    viewModel.currentpage == 0
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = Name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            alignment= Alignment.TopCenter
        )
        Text(
            text = Name,
            fontSize = 30.sp
            )
        Stars(num = stars,30)
        Text(bio)
        LocationWebView(location = location)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
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
                    val newl = fromLocation(location)
                    val favoriteHotel = HotelR(
                        name = Name,
                        image = imageResource,
                        bio = bio,
                        location = newl,
                        stars = stars
                    )
                    viewModel.addHotelToFavorites(favoriteHotel)
                }
            ) {
                Text("Add to Favorite")
            }
        }
    }
}

@Composable
fun LocationWebView(location: Location) {
    val hotel = LatLng(location.latitude, location.longitude)
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

fun fromLocation(location: Location): String {
    return "${location.latitude},${location.longitude}"
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