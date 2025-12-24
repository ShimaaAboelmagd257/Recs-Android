package com.example.recs.presentation.movie

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.mini_netflix.R
import com.example.recs.utility.Const

@Composable
fun MovieWithDetailsView(viewModel: MovieDetailsViewModel = hiltViewModel(), movieId:Int,onRateClicked:()-> Unit) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getMovieDetails(movieId)
    }
    when(val movieState = state){
        is MovieDetailStatus.Error -> {
            Image(
            painter = painterResource(id = R.drawable.welcome_logo),
            contentDescription = "ERROR",
            modifier = Modifier.size(300.dp),
            contentScale = ContentScale.Crop

        )
            Text(
                text = "Error loading movies",
                color = Color.Red,
                modifier = Modifier.padding(16.dp)
            )
            Log.e(Const.APP_LOGS,"Error in Home")

        }
        MovieDetailStatus.Loading -> {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(50.dp),
                    color = Color.Black)
                Log.w(Const.APP_LOGS,"LOADING in MovieDetails")
            }
        }
        is MovieDetailStatus.Success -> {
            val data = movieState.data
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500${data.poster_path}",
                        contentDescription = data.title,
                        contentScale = ContentScale.FillBounds,

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp)
                            .clip(RoundedCornerShape(20.dp))
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = data.title ?: "",
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Button(
                        onClick = onRateClicked,
                        colors = ButtonDefaults.buttonColors(containerColor =  Color.Red),
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text =  "Rate the movie")
                        Log.d(Const.APP_LOGS,"onRateClicked")

                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                    ) {
                        Text(text = "⭐ ${"%.1f".format(data.vote_average)}", fontSize = 18.sp)
                        Spacer(modifier = Modifier.width(15.dp))
                        Text(text = "🔥 ${data.popularity.toInt()} popularity", fontSize = 16.sp)
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth(),

                    ) {
                        val hours = data.runtime / 60
                        val minutes = data.runtime % 60
                        Text(text = "⏳ ${hours}h ${minutes}m", fontSize = 15.sp)
                        Spacer(modifier = Modifier.width(15.dp))
                        Text(text = "📅 ${data.releaseDate ?: ""}", fontSize = 15.sp)
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    // --- Genres ---
                    if (!data.genres.isNullOrEmpty()) {
                        FlowRow(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            data.genres!!.forEach { genre ->
                                Box(
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .background(Color.LightGray, RoundedCornerShape(12.dp))
                                        .padding(horizontal = 10.dp, vertical = 6.dp)
                                ) {
                                    Text(text = genre.name ?: "", fontSize = 14.sp)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // --- Overview ---
                    Text(
                        text = "Overview",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.fillMaxWidth().padding(10.dp)
                    )

                    Text(
                        text = data.overview ?: "",
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )

                    Spacer(modifier = Modifier.height(15.dp))


                }
            }
        }
    }
}