package com.example.recs.presentation.home

import com.example.recs.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeUseCases @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke():HomeStatus{
        return withContext(Dispatchers.IO) {
            val data = repository.getAllMovies()
            HomeStatus.Success(data = data)
        }
    }
}