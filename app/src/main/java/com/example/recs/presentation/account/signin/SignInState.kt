package com.example.recs.presentation.account.signin
sealed class SignInState {
    data object Idle : SignInState()
    data object Loading : SignInState()
    data object Success : SignInState()
    data  object UserNotFound : SignInState()
    data object WrongPassword : SignInState()
    data class Error(val errorMessage: String) : SignInState()
}