package com.example.recs.presentation.account.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) :ViewModel(){
    private val _viewState = MutableStateFlow<SignUpState>(SignUpState.Loading)
    val viewState: StateFlow<SignUpState> = _viewState

    fun processSignUp(intent: SignUpIntent) {
        _viewState.value = SignUpState.Loading
        viewModelScope.launch {
            try {
                val result = signUpUseCase.signupWithEmail(intent)
                _viewState.value =
                    if (result) SignUpState.Success else SignUpState.Error("Failed to sign up")
            } catch (e: Exception) {
                _viewState.value = SignUpState.Error(e.message ?: "Unknown error")
            }

        }
    }


}