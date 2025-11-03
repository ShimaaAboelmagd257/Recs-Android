package com.example.recs.data.repository

import com.example.recs.data.api.RemoteSource
import com.example.recs.data.model.Genre
import com.example.recs.data.model.Genres
import com.example.recs.data.model.Movie
import com.example.recs.data.model.Rating
import com.example.recs.data.model.TmdbApiResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val remoteSource: RemoteSource) {

    suspend fun getAllMovies() :List<Movie>{
       return remoteSource.getAllMovies().results
    }
     suspend fun getGenres(): Genres {
        return remoteSource.getGenres()
    }

     suspend fun getMoviesByGenre(genreId: Int): TmdbApiResponse {
        return remoteSource.getMoviesByGenre(genreId)
    }

     suspend fun getMovieDetails(movieId: Int): TmdbApiResponse {
        return remoteSource.getMovieDetails(movieId)
    }

     suspend fun submitRating(rating: Rating): Rating {
        return remoteSource.submitRating(rating)
    }

     suspend fun getRatingByUser(userId: Long): List<Rating> {
        return remoteSource.getRatingByUser(userId)
    }

     suspend fun getRecsBasedByGenres(genreIds: List<Int>): Movie {
        return remoteSource.getRecsBasedByGenres(genreIds)
    }

     suspend fun getRecommendationsForUser(userId: Long): Movie {
        return remoteSource.getRecommendationsForUser(userId)
    }


}