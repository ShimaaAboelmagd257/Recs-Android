package com.example.recs.presentation.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mini_netflix.R
import com.example.recs.presentation.movie.MovieCard
import com.example.recs.utility.Const
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeView(viewModel: HomeViewModel = hiltViewModel(), onMovieClicked:()-> Unit) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getPopularMovies()
    }

    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    
    when(val homeStatus = state){
        is HomeStatus.Error -> {
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
            Log.e(Const.APP_LOGS,"Error in Home")
        }
        HomeStatus.Loading -> {
           CircularProgressIndicator(modifier = Modifier.padding(50.dp))
            Log.e(Const.APP_LOGS,"LOADING in Home")

        }
        is HomeStatus.Success -> {
            val movies = homeStatus.data
            HorizontalPager(
               count = movies.size,
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) {
                page ->
                MovieCard(
                    movies[page],
                    onMovieCardClicked = { onMovieClicked() }
                )

                }

            Log.e(Const.APP_LOGS,"SUCCESS Home : movies.size = ${movies.size}")
            }
        }
    }




    
