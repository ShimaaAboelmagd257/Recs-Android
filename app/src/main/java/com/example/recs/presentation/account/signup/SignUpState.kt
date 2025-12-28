package com.example.recs.presentation.account.signup


sealed class SignUpState {
    data object Idle : SignUpState()

    data object Loading : SignUpState()
    data object Success : SignUpState()
    data class Error(val error:String) : SignUpState()
}