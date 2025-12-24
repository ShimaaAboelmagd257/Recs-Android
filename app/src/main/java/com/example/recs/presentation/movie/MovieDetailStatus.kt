package com.example.recs.presentation.movie

import com.example.recs.data.model.Movie
import com.example.recs.data.model.MovieDetailsResponse

sealed class MovieDetailStatus {
    data object Loading: MovieDetailStatus()
    data class Success(val data: MovieDetailsResponse) : MovieDetailStatus()
    data class Error(val error:String): MovieDetailStatus()
}