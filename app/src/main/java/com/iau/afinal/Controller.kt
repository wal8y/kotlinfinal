package com.iau.afinal

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iau.afinal.pages.FavPage
import com.iau.afinal.pages.HotelsPage
import com.iau.afinal.pages.home
import com.iau.afinal.pages.hotelPage
import com.iau.afinal.data.hotels
import com.iau.afinal.pages.FavViewModel
import com.iau.afinal.pages.HotelsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Controllers(navController: NavHostController = rememberNavController()) {
    val favViewModel: FavViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val hotelsViewModel: HotelsViewModel = viewModel(factory = AppViewModelProvider.Factory)
    NavHost(navController = navController, startDestination = Pages.home.name) {
        composable(route = Pages.home.name) {
            home(navHostController = navController, hotelsViewModel)
        }
        composable(route = Pages.hotels.name) {
            HotelsPage(navController = navController, viewModel = hotelsViewModel)
        }
        composable(route = "${Pages.hotel.name}/{hotelName}") { backStackEntry ->
            val hotelName = backStackEntry.arguments?.getString("hotelName")
            val hotel = hotels.find { it.Name == hotelName }
            if (hotel != null) {
                hotelPage(
                    Name = hotel.Name,
                    imageResource = hotel.imageResource,
                    bio = hotel.bio,
                    location = hotel.location,
                    stars = hotel.stars,
                    navController = navController,
                    viewModel = favViewModel
                )
            }
        }
        composable(route = Pages.fav.name) {
            FavPage(navController = navController, viewModel = favViewModel)
        }
    }
}
