package com.iau.afinal.pages

import android.location.Location
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    location: String,
    stars: Int,
    navController: NavHostController,
    viewModel: MyViewModel
) {
    viewModel.currentpage == 0
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = Name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .border(width = 10.dp,
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0,115,205)
                ),
            alignment= Alignment.TopCenter
        )
        Text(
            text = Name,
            fontSize = 30.sp,
            modifier = Modifier.padding(20.dp)
            )
        Stars(num = stars,30)
        Text(bio, modifier = Modifier.padding(20.dp))
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
                    val favoriteHotel = HotelR(
                        name = Name,
                        image = imageResource,
                        bio = bio,
                        location = location,
                        stars = stars
                    )
                    viewModel.addHotelToFavorites(favoriteHotel)
                    navController.popBackStack()
                }
            ) {
                Text("Add to Favorite")
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