package com.example.recs.presentation.movie

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.recs.data.model.Movie
import com.example.recs.utility.Const

@Composable
fun MovieCard(
    movie: Movie,
    onMovieCardClicked:()-> Unit,
) {

    Card(
        modifier = Modifier
            .size(450.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(35.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)

    ) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
            contentDescription = movie.title,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxWidth().height(290.dp).clip(RoundedCornerShape(20.dp))
        )
        Column(
            modifier = Modifier.padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Text(text = movie.title, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Text(
                text = "⭐ ${"%.1f".format(movie.vote_average)}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Button(
                onClick = onMovieCardClicked,
                colors = ButtonDefaults.buttonColors(containerColor =  Color.Red),
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Text(text =  "Watch Now")
                Log.d(Const.APP_LOGS,"onMovieCardClicked")

            }



        }

    }
}