package com.example.recs.presentation.account.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
):ViewModel() {

    private val _state = MutableStateFlow<SignInState>(SignInState.Idle)
    val state: StateFlow<SignInState> = _state

     fun signIn(email:String,password:String){
        _state.value = SignInState.Loading

        viewModelScope.launch {
            try {
                val result = signInUseCase.signIn(email,password)
                _state.value = result
            }catch (e:Exception){
                _state.value = SignInState.Error(e.message ?: "Error")

            }
        }
    }

}