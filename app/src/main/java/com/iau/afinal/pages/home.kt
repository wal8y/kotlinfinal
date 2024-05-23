package com.iau.afinal.pages

import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.iau.afinal.MyViewModel
import com.iau.afinal.R

@ExperimentalMaterial3Api
@Composable
fun home(navHostController: NavHostController , viewModel: MyViewModel){
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .border(
                        border = BorderStroke(2.dp, Color.Black),
                        shape = RoundedCornerShape(20.dp),
                    )
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0,115,205),
                                Color(0,155,205)
                            )
                        ),
                        RoundedCornerShape(20.dp)
                    )
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(1.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Welcome to Otelista\n\n", fontSize = 20.sp)
                    Text(
                        text = "You can also Checkout ...",
                        fontSize = 22.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }
            } },
        bottomBar = { NavBar(navController = navHostController, viewModel = viewModel) }
    ) {
        innerPadding ->
        Recommendation(
            contentPadding = innerPadding,
        )
    }
}

@Composable
fun Recommendation(
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    val images = listOf(
        R.drawable.github_logo,
        R.drawable.codizy_logo,
        R.drawable.trendyol_logo,
        R.drawable.hotels_logo,
        R.drawable.github_logo,
        R.drawable.codizy_logo,
        R.drawable.trendyol_logo,
        R.drawable.hotels_logo,
        R.drawable.github_logo,
        R.drawable.codizy_logo,
        R.drawable.trendyol_logo,
        R.drawable.hotels_logo,
        R.drawable.github_logo,
        R.drawable.codizy_logo,
        R.drawable.trendyol_logo,
        R.drawable.hotels_logo
    )
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = contentPadding,
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                items(images) { imageUrl ->
                    ImageWithWebView(imageResource = imageUrl)
                }
            }
        }
    }
}



@Composable
fun ImageWithWebView(imageResource: Int) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                val url = when (imageResource) {
                    R.drawable.github_logo -> "https://github.com/wal8y"
                    R.drawable.codizy_logo -> "https://play.google.com/store/apps/details?id=com.Codizy2.codizy&hl=en_US&pli=1"
                    R.drawable.trendyol_logo -> "https://www.trendyol.com/"
                    R.drawable.hotels_logo -> "https://Hotels.com/"
                    else -> "https://github.com/wal8y"
                }
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                context.startActivity(intent)
            }
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(15.dp)),
            contentScale = ContentScale.Fit
        )
    }
}