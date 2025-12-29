package com.example.recs.utility

object Const {
    const val APP_LOGS = "MY APP LOGS"
    const val USER_ID ="userId"
    const val SAVED_SIGN_IN = "signIn"
    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}\$")
        return emailRegex.matches(email)
    }

    fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }

}
