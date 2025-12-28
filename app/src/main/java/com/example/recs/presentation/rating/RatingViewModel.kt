package com.example.recs.presentation.rating

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recs.data.model.Rating
import com.example.recs.utility.Const
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RatingViewModel @Inject constructor(private val useCase: RatingUseCase):ViewModel() {

    private val _state = MutableStateFlow<RatingStatus>(RatingStatus.Loading)
    val state: StateFlow<RatingStatus> = _state

    private val _rateState = MutableStateFlow<SubmitRatingStatus>(SubmitRatingStatus.Loading)
    val rateState: StateFlow<SubmitRatingStatus> = _rateState

    fun getRatingsByUser(userId:Long){
        viewModelScope.launch {
            try {
                val result = useCase.invoke(userId)
                _state.value = result
                Log.d(Const.APP_LOGS, "Rating ViewModel SUCCESS")

            }catch (e:Exception){
                Log.e(Const.APP_LOGS, e.message?:"Exception Error Rating")
            }

        }
    }

    fun submitRating( movieId: Int,rating:Double){
        val userId = 12121111112
        viewModelScope.launch {
            try {
                val result = useCase.submitRating(Rating(
                    userId = userId,
                    rating = rating,
                    movieId = movieId
                ))
                _rateState.value = result
            }catch (e:Exception){
                Log.e(Const.APP_LOGS, e.message?:"Exception Error Rating")

            }
        }

    }
}