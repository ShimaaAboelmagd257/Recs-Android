package com.example.recs.presentation.genre

import com.example.recs.data.repository.Repository
import com.example.recs.presentation.home.HomeStatus
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
}