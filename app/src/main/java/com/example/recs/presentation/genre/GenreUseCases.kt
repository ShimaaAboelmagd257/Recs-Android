package com.example.recs.presentation.genre

import android.util.Log
import com.example.recs.data.repository.Repository
import com.example.recs.utility.Const
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GenreUseCases @Inject constructor(private val repository :Repository) {

    suspend operator fun invoke(): GenreStatus {
        return withContext(Dispatchers.IO) {
            val data = repository.getGenres()
            GenreStatus.Success(data = data)
        }
    }
    suspend fun getMoviesByGenre(genreId:Int):MovieByGenreStatus{
        return withContext(Dispatchers.IO) {
            val data = repository.getMoviesByGenre(genreId)
            Log.d(Const.APP_LOGS, "GenreUseCases  SUCCESS with movies : ${data.results.size}")

            MovieByGenreStatus.Success(data = data.results)
        }
    }
}