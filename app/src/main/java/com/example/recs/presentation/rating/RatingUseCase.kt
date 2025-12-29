package com.example.recs.presentation.rating

import com.example.recs.data.model.Movie
import com.example.recs.data.model.Rating
import com.example.recs.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RatingUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(userId: String): RatingStatus {
        return withContext(Dispatchers.IO) {
            val data = repository.getRatingByUser(userId)
            val movies:List<Movie> = data.map { id ->
                val details=repository.getMovieDetails(movieId = id.movieId)
                Movie(
                    id = details.id,
                    title = details.title,
                    poster_path = details.poster_path,
                    vote_average = id.rating
                )
            }
            RatingStatus.Success(data = movies)
        }
    }

    suspend fun submitRating(rating: Rating):SubmitRatingStatus{
       return withContext(Dispatchers.IO){
            repository.submitRating(rating)
           SubmitRatingStatus.Success(rating = rating)
        }
    }
}