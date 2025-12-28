package com.example.recs.presentation.account.signin

import com.example.recs.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignInUseCase  @Inject constructor(private  val repository: Repository){
    suspend fun signIn(email:String,password:String): SignInState {
        return withContext(Dispatchers.IO) {
            if (!isValidInput(email,password)) {
                return@withContext SignInState.Error("NotValidInput")
            }
            val userExists = repository.signIn(email,password)
            return@withContext userExists
        }
    }



    private fun isValidInput(email:String,password:String): Boolean {
        return email.isNotBlank() && password.isNotBlank()
    }

}