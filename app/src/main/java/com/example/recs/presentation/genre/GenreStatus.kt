package com.example.recs.presentation.genre
import com.example.recs.data.model.Genres

sealed class GenreStatus {
    data object Loading: GenreStatus()
    data class Success(val data: Genres) : GenreStatus()
    data class Error(val error:String): GenreStatus()

}