package com.example.recs.presentation.genre

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recs.utility.Const
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GenreViewModel @Inject constructor(private val genreUseCases: GenreUseCases):ViewModel(){

      private val _state = MutableStateFlow<GenreStatus>(GenreStatus.Loading)
    val state: StateFlow<GenreStatus> = _state
    private val _moviesState = MutableStateFlow<MovieByGenreStatus>(MovieByGenreStatus.Loading)
    val moviesState: StateFlow<MovieByGenreStatus> = _moviesState
    fun getGenres(){
        viewModelScope.launch {
            try {
                val result = genreUseCases.invoke()
                _state.value = result
                Log.d(Const.APP_LOGS, "Genre ViewModel SUCCESS")

            }catch (e:Exception){
                Log.e(Const.APP_LOGS, e.message?:"GenreViewModel Exception Error")
            }

        }
    }
    fun getMoviesByGenre(genreId : Int){
        viewModelScope.launch {
            try {
                val result = genreUseCases.getMoviesByGenre(genreId)
                _moviesState.value = result
                Log.d(Const.APP_LOGS, "getMoviesByGenre ViewModel SUCCESS", )

            }catch (e:Exception){
                Log.e(Const.APP_LOGS, e.message?:"GenreViewModel Exception Error")
            }
        }
    }
}