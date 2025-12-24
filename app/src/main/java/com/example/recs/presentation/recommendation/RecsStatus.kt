package com.example.recs.presentation.recommendation

import com.example.recs.data.model.Movie

sealed class RecsStatus {
    data object Loading: RecsStatus()
    data class Success(val data: List<Movie>) : RecsStatus()
    data class Error(val error:String): RecsStatus()

}