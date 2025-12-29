package com.example.recs.presentation.home

import com.example.recs.data.model.Movie

sealed class HomeStatus {

    data object Loading:HomeStatus()
    data class Success(val data: List<Movie>) :HomeStatus()
    data class Error(val error:String):HomeStatus()
}