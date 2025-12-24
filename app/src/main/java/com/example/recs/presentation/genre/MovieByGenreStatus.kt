package com.example.recs.presentation.genre
import com.example.recs.data.model.Movie

sealed class MovieByGenreStatus {
    data object Loading: MovieByGenreStatus()
    data class Success(val data: List<Movie>) : MovieByGenreStatus()
    data class Error(val error:String): MovieByGenreStatus()

}