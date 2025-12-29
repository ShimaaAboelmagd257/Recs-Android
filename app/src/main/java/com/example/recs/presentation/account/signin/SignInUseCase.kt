package com.example.recs.presentation.account.signin

import com.example.recs.data.repository.Repository
import com.example.recs.data.sharedprefrence.SharedPreference
import com.example.recs.utility.Const
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignInUseCase  @Inject constructor(private  val repository: Repository , private val sharedPreference: SharedPreference){

    suspend fun signIn(email:String,password:String): SignInState {
        return withContext(Dispatchers.IO) {
            if (!isValidInput(email,password)) {
                return@withContext SignInState.Error("NotValidInput")
            }
            val userExists = repository.signIn(email,password)
            sharedPreference.addBoolean(Const.SAVED_SIGN_IN,true)
            return@withContext userExists
        }
    }

    private fun isValidInput(email:String,password:String): Boolean {
        return email.isNotBlank() && password.isNotBlank()
    }

}