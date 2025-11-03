package com.example.recs.presentation.genre

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mini_netflix.R
import com.example.recs.utility.navigation.NavigationRoute


data class CardContent (
    val title:String,
    val size : Dp,
    val image: Painter,
    val route:String
)

@Composable
fun genreCardsContent():List<CardContent> {

    val cards = listOf(
        CardContent(
            "Action" , 180.dp, painterResource(R.drawable.ic_launcher_foreground), NavigationRoute.Home.route
        ),
        CardContent(
            "Comedy" , 210.dp, painterResource(R.drawable.ic_launcher_foreground), NavigationRoute.Home.route
        ),
        CardContent(
            "Romance" , 180.dp, painterResource(R.drawable.ic_launcher_foreground), NavigationRoute.Home.route
        ),
        CardContent(
            "Drama" , 180.dp, painterResource(R.drawable.ic_launcher_foreground), NavigationRoute.Home.route
        )

    )
    return  cards
}