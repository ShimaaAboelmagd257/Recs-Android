package com.example.recs.data.api

import com.example.recs.data.model.Genres
import com.example.recs.data.model.Movie
import com.example.recs.data.model.MovieDetailsResponse
import com.example.recs.data.model.Rating
import com.example.recs.data.model.TmdbApiResponse
import com.example.recs.data.model.MovieRecsId
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RecService {

    @GET("movies/popular")
    suspend fun getPopularMovies():TmdbApiResponse
    @GET("movies/genres")
    suspend fun getGenres(): Genres
    @GET("movies/genres/{genreId}")
    suspend fun getMoviesByGenre(@Path("genreId") genreId:Int):TmdbApiResponse
    @GET("movies/{movieId}")
    suspend fun getMovieDetails( @Path("movieId")movieId:Int): MovieDetailsResponse
    @POST("ratings")
    suspend fun submitRating(rating: Rating):Rating
    @GET("ratings/user/{userId}")
    suspend fun getRatingByUser(
        @Path("userId") userId: Long
    ):List<Rating>
    @GET("recommendations/by_genre")
    suspend fun getRecsBasedByGenres(genreIds:List<Int>):List<Movie>
    @GET("recommendations/{userId}")
    suspend fun getRecommendationsForUser(
        @Path("userId") userId: Long,
        @Query("limit") limit: Int = 10):List<MovieRecsId>
}