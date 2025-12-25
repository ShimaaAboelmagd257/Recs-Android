package com.example.recs.presentation.account.signup


data class SignUpIntent(
    val name: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)

