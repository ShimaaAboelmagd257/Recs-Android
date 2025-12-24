package com.example.recs.presentation.recommendation

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
class RecsViewModel @Inject constructor(private val recsUseCase: RecsUseCase  ): ViewModel() {


    private val _state = MutableStateFlow<RecsStatus>(RecsStatus.Loading)
    val state: StateFlow<RecsStatus> = _state

    fun getRecommendationForUser(userId:Long){
        viewModelScope.launch {
            try {
                val result = recsUseCase.invoke(userId)
                _state.value = result
                Log.e(Const.APP_LOGS, "Recs ViewModel SUCCESS")

            }catch (e:Exception){
                Log.e(Const.APP_LOGS, e.message?:"Exception Error")
            }

        }
    }
}