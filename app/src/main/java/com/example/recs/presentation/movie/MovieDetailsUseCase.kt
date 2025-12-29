package com.example.recs.presentation.movie

import com.example.recs.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDetailsUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(movieId:Int): MovieDetailStatus {
        return withContext(Dispatchers.IO) {
            val data = repository.getMovieDetails(movieId)
            MovieDetailStatus.Success(data = data)
        }
    }
}