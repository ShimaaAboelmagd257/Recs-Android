package com.example.recs.presentation.account.profile

import com.example.recs.data.model.User

sealed class ProfileState {
    data object Loading:ProfileState()
    data class Success(val user:User):ProfileState()
    data class Error(val error:String):ProfileState()
}