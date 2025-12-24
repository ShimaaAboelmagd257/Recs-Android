package com.example.recs.presentation.genre

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.mini_netflix.R
import com.example.recs.presentation.movie.MovieCard
import com.example.recs.utility.Const


@Composable
fun MoviesByGenreView(
    viewModel: GenreViewModel = hiltViewModel(),
    genreId:Int,
    onMovieClicked:()-> Unit

) {
    val scroll = rememberScrollState()

    val moviesState by viewModel.moviesState .collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getMoviesByGenre(genreId)
    }
    when(val state = moviesState){
        is MovieByGenreStatus.Error -> {
            Image(
                painter = painterResource(id = R.drawable.utl),
                contentDescription = "ERROR",
                modifier = Modifier.size(300.dp),
                contentScale = ContentScale.Crop

            )
            Text(
                text = "Error loading movies",
                color = Color.Red,
                modifier = Modifier.padding(16.dp)
            )
            Log.e(Const.APP_LOGS,"Error in Movies By Genre")
        }
        MovieByGenreStatus.Loading -> {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(50.dp),
                    color = Color.Black)
                Log.w(Const.APP_LOGS,"LOADING in MoviesByGenre")
            }

        }
        is MovieByGenreStatus.Success -> {
            val movies = state.data
            Column  (
                modifier = Modifier.fillMaxSize().verticalScroll(scroll)
            ) {

                Spacer(modifier = Modifier.height(15.dp))
                movies.forEach { movie ->
                    MovieCard(
                        movie,
                        onMovieCardClicked = { onMovieClicked() }
                    )
                }


            }

        }
    }

}