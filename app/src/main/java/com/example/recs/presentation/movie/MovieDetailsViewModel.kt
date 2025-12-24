package com.example.recs.presentation.movie

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
class MovieDetailsViewModel @Inject constructor(private val  movieDetailsUseCase: MovieDetailsUseCase): ViewModel() {


    private val _state = MutableStateFlow<MovieDetailStatus>(MovieDetailStatus.Loading)
    val state: StateFlow<MovieDetailStatus> = _state

    fun getMovieDetails(movieId:Int){
        viewModelScope.launch {
            try {
                val result = movieDetailsUseCase.invoke(movieId)
                _state.value = result
                Log.e(Const.APP_LOGS, "MovieDetailsViewModel SUCCESS")

            }catch (e:Exception){
                Log.e(Const.APP_LOGS, e.message?:"Exception Error")
            }

        }
    }
}