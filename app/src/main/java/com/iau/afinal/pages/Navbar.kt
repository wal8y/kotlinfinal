package com.iau.afinal.pages
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.iau.afinal.Pages

@Composable
fun NavBar(navController: NavHostController, viewModel: ViewModel) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(10.dp)
    ) {
        FloatingActionButton(
            onClick = {
                navController.navigate(Pages.hotels.name)
            },
            modifier = Modifier.size(50.dp),
            containerColor = Color(0, 115, 205)
        ) {
            Icon(imageVector = Icons.Default.List, contentDescription = "list")
        }
        FloatingActionButton(
            onClick = {
                navController.navigate(Pages.home.name)
            },
            modifier = Modifier.size(50.dp),
            containerColor = Color(0, 115, 205)
        ) {
            Icon(imageVector = Icons.Default.Home, contentDescription = "home")
        }
        FloatingActionButton(
            onClick = {
                navController.navigate(Pages.fav.name)
            },
            modifier = Modifier.size(50.dp),
            containerColor = Color(0, 115, 205)
        ) {
            Icon(imageVector = Icons.Default.Favorite, contentDescription = "fav")
        }
    }
}
