package com.example.recs.presentation.account.signup

import com.example.recs.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignUpUseCase @Inject constructor(val repository: Repository){

    suspend fun signupWithEmail(intent: SignUpIntent): Boolean {
        return withContext(Dispatchers.IO) {
            if (!isValidInput(intent)) {
                return@withContext false
            }
            val userExists = repository.checkUserExists(intent.email)
            if (userExists) {
                return@withContext false
            }
            val isInserted  = repository.signUp(intent.name,intent.email,intent.password)
            return@withContext isInserted
        }
    }

    private fun isValidInput(intent: SignUpIntent): Boolean {
        return intent.email.isNotBlank() &&
                intent.password.isNotBlank() &&
                intent.password == intent.confirmPassword
    }

}