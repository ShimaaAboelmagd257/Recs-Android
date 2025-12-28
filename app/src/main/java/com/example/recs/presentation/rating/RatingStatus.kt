package com.example.recs.presentation.rating

import com.example.recs.data.model.Movie
import com.example.recs.data.model.Rating

sealed class RatingStatus {
    data object Loading: RatingStatus()
    data class Success(val data: List<Movie>) : RatingStatus()
    data class Error(val error:String): RatingStatus()

}
sealed class SubmitRatingStatus {
    data object Loading: SubmitRatingStatus()
    data class Success(val rating: Rating) : SubmitRatingStatus()
    data class Error(val error:String): SubmitRatingStatus()

}