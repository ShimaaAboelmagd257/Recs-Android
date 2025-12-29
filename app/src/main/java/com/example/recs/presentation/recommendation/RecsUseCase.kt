package com.example.recs.presentation.recommendation

import com.example.recs.data.model.Movie
import com.example.recs.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecsUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(userId:String): RecsStatus {
        return withContext(Dispatchers.IO) {
            val recsIds = repository.getRecommendationsForUser(userId)
           val movies:List<Movie> = recsIds.map{ id ->
             val details=repository.getMovieDetails(id.movieId)
               Movie(
                   id = details.id,
                   title = details.title,
                   poster_path = details.poster_path,
                   vote_average = details.vote_average
               )
            }
            RecsStatus.Success(data = movies )
        }
    }
}