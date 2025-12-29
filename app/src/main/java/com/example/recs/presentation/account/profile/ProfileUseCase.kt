package com.example.recs.presentation.account.profile

import com.example.recs.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileUseCase @Inject constructor(private val repository: Repository) {


     suspend fun getUserInfo(uid:String): ProfileState {
        return withContext(Dispatchers.IO) {
            val user = repository.getUserInfo(uid) !!
            return@withContext ProfileState.Success(user = user)
        }
    }
     fun signOut(){
         repository.signOut()
    }


}