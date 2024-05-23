package com.iau.afinal.pages
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.iau.afinal.MyViewModel
import com.iau.afinal.Pages
import kotlin.math.absoluteValue

@Composable
fun NavBar(navController: NavHostController, viewModel: MyViewModel) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White).padding(10.dp)
        ) {
            FloatingActionButton(
                onClick = {
                    viewModel.currentpage = 1
                    navController.navigate(Pages.hotels.name)
                },
                modifier = Modifier.size(50.dp)
                ,containerColor = Color(0,115,205)
            ){
                Icon(imageVector = Icons.Default.List, contentDescription = "list")
            }
            FloatingActionButton(
                onClick = {
                    viewModel.currentpage = 0
                    navController.navigate(Pages.home.name)
                },
                modifier = Modifier.size(50.dp)
                ,containerColor = Color(0,115,205)
            ){
                Icon(imageVector = Icons.Default.Home, contentDescription = "home")
            }
            FloatingActionButton(
                onClick = {
                    viewModel.currentpage = 2
                    navController.navigate(Pages.fav.name)
                },
                modifier = Modifier.size(50.dp)
                ,containerColor = Color(0,115,205)
            ){
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "fav")
            }
        }
}
