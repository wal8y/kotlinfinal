package com.iau.afinal.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.iau.afinal.MyViewModel
import com.iau.afinal.R

@Composable
fun user(navController: NavHostController, viewModel : MyViewModel){
    viewModel.currentpage == 2
    Surface(
        modifier = Modifier.width(34.dp),
        ){
        Row(
            modifier = Modifier
                .border(
                    border = BorderStroke(2.dp, Color.Black),
                    shape = RoundedCornerShape(1.dp)
                )
                .background(Color.LightGray)
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(text = "Hello "+"user"+"\nWelcome to Otelista")
            }
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .size(100.dp),
                painter = painterResource(R.drawable.user),
                contentDescription = "user")
        }
    }
}