package com.example.recs.presentation.rating

import com.example.recs.data.model.Movie

sealed class RatingStatus {
    data object Loading: RatingStatus()
    data class Success(val data: List<Movie>) : RatingStatus()
    data class Error(val error:String): RatingStatus()

}