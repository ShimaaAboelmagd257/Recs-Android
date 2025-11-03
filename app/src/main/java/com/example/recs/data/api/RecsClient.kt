package com.example.recs.data.api

import com.example.recs.data.model.Genres
import com.example.recs.data.model.Movie
import com.example.recs.data.model.Rating
import com.example.recs.data.model.TmdbApiResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecsClient @Inject constructor(): RemoteSource {

    private val recService:RecService by lazy {
        RetrofitHelper.retrofitInstance.create(RecService::class.java)
    }

    override suspend fun getAllMovies(): TmdbApiResponse {
        return recService.getPopularMovies()
    }

    override suspend fun getGenres(): Genres {
        return recService.getGenres()
    }

    override suspend fun getMoviesByGenre(genreId: Int): TmdbApiResponse {
        return recService.getMoviesByGenre(genreId)
    }

    override suspend fun getMovieDetails(movieId: Int): TmdbApiResponse {
       return recService.getMovieDetails(movieId)
    }

    override suspend fun submitRating(rating: Rating): Rating {
        return recService.submitRating(rating)
    }

    override suspend fun getRatingByUser(userId: Long): List<Rating> {
        return recService.getRatingByUser(userId)
    }

    override suspend fun getRecsBasedByGenres(genreIds: List<Int>): Movie {
        return recService.getRecsBasedByGenres(genreIds)
    }

    override suspend fun getRecommendationsForUser(userId: Long): Movie {
        return recService.getRecommendationsForUser(userId)
    }


}