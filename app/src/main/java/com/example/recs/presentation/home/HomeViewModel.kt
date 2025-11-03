package com.example.recs.presentation.home

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
class HomeViewModel @Inject constructor(val  homeUseCases: HomeUseCases):ViewModel() {


    private val _state = MutableStateFlow<HomeStatus>(HomeStatus.Loading)
    val state: StateFlow<HomeStatus> = _state

    fun getPopularMovies(){
        viewModelScope.launch {
            try {
                val result = homeUseCases.invoke()
                _state.value = result
                Log.e(Const.APP_LOGS, "Home ViewModel SUCCESS")

            }catch (e:Exception){
                Log.e(Const.APP_LOGS, e.message?:"Exception Error")
            }

        }
    }
}