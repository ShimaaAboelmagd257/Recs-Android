package com.example.recs.data.api

import com.example.recs.data.model.Genres
import com.example.recs.data.model.Movie
import com.example.recs.data.model.MovieDetailsResponse
import com.example.recs.data.model.Rating
import com.example.recs.data.model.TmdbApiResponse
import com.example.recs.data.model.MovieRecsId
import javax.inject.Singleton

@Singleton
interface RemoteSource {
    suspend fun getAllMovies():TmdbApiResponse
    suspend fun getGenres(): Genres
    suspend fun getMoviesByGenre(genreId:Int):TmdbApiResponse
    suspend fun getMovieDetails(movieId:Int): MovieDetailsResponse
    suspend fun submitRating(rating: Rating): Rating
    suspend fun getRatingByUser(userId:String):List<Rating>
    suspend fun getRecsBasedByGenres(genreIds:List<Int>): List<Movie>
    suspend fun getRecommendationsForUser(userId:String): List<MovieRecsId>
}