package com.iau.afinal.pages
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
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
    //BottomAppBar {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            NavButton(
                onClick = {
                    viewModel.currentpage = 1
                    navController.navigate(Pages.hotels.name)
                },
                isSelected = viewModel.currentpage == 1,
                icon = Icons.Default.List
            )
            NavButton(
                onClick = {
                    viewModel.currentpage = 0
                    navController.navigate(Pages.home.name)
                },
                isSelected = viewModel.currentpage == 0,
                icon = Icons.Default.Home
            )
            NavButton(
                onClick = {
                    viewModel.currentpage = 3
                    navController.navigate(Pages.fav.name)
                },
                isSelected = viewModel.currentpage == 3,
                icon = Icons.Default.Favorite
            )
            NavButton(
                onClick = {
                    viewModel.currentpage = 2
                    navController.navigate(Pages.user.name)
                },
                isSelected = viewModel.currentpage == 2,
                icon = Icons.Default.AccountCircle
            )
        }
   // }
}


@Composable
fun NavButton(
    onClick: () -> Unit,
    isSelected: Boolean,
    icon: ImageVector
) {
    var size = 50.dp
    var pad = 0.dp
    val buttonColor by animateColorAsState(
        if (isSelected) Color.DarkGray else Color.White,
        animationSpec = tween(durationMillis = 700)
    )
    if (isSelected){
        size = 60.dp
        pad = 2.dp
    }

    FloatingActionButton(
        onClick = onClick,
        shape = MaterialTheme.shapes.extraLarge,
        containerColor = buttonColor,
        modifier = Modifier
            .padding(horizontal = 18.dp)
            .size(size).padding(bottom = pad)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isSelected) Color.White else Color.DarkGray
        )
    }
}