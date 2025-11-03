package com.example.recs.data.api

import com.example.recs.data.model.Genre
import com.example.recs.data.model.Movie
import com.example.recs.data.model.Rating
import com.example.recs.data.model.TmdbApiResponse
import retrofit2.http.GET
import retrofit2.http.POST

interface RecService {

    @GET("movies/popular")
    suspend fun getPopularMovies():TmdbApiResponse
    @GET("genres")
    suspend fun getGenres():Genre
    @GET("genres/{genreId}")
    suspend fun getMoviesByGenre(genreId:Int):TmdbApiResponse
    @GET("{movieId}")
    suspend fun getMovieDetails(movieId:Int):TmdbApiResponse
    @POST("ratings")
    suspend fun submitRating(rating: Rating):Rating
    @GET("user/{userId}")
    suspend fun getRatingByUser(userId:Long):List<Rating>
    @GET("recommendations/by_genre")
    suspend fun getRecsBasedByGenres(genreIds:List<Int>):Movie
    @GET("recommendations/{userId}")
    suspend fun getRecommendationsForUser(userId:Long):Movie
}