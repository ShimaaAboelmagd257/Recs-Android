package com.example.recs.data.firebase

import android.util.Log
import com.example.recs.data.model.User
import com.example.recs.data.sharedprefrence.SharedPreference
import com.example.recs.presentation.account.signin.SignInState
import com.example.recs.utility.Const
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseManager @Inject constructor(private val sharedPreference: SharedPreference){

    private val mAuth:FirebaseAuth = FirebaseAuth.getInstance()
    private val fireStore:FirebaseFirestore = FirebaseFirestore.getInstance()
    private val userCollection: CollectionReference = fireStore.collection("users")


    suspend fun signIn(email:String, password:String):SignInState{
        return try {
            val result = mAuth.signInWithEmailAndPassword(email,password).await()
            val userId = result.user?.uid?:throw IllegalStateException("User Id Null")
            sharedPreference.addString(Const.USER_ID,userId)
            SignInState.Success

        }catch (e:Exception){
            SignInState.Error(e.message?:"Error sign in ${e.message}")

        }

    }
    suspend fun signUp(name:String,email:String,password: String):Boolean{
        return try {
            val result = mAuth.createUserWithEmailAndPassword(email,password).await()
            val userId = result.user?.uid?: throw IllegalStateException("User Id NULL")
           val user = User(uid = userId,email = email, name = name)
            userCollection.document(userId).set(user).await()
            sharedPreference.addString(Const.USER_ID,userId)
            true
        }catch (e:Exception){
            Log.e(Const.APP_LOGS,"Error ${e.message}")
            false

        }
    }

    fun signOut(){
        mAuth.signOut()
    }
    suspend fun getUserInfo(uid:String):User?{
        return try {
            val snapshot = userCollection.document(uid).get().await()
            snapshot.toObject(User::class.java)?: throw IllegalStateException("User Not Found")
        }catch (e:Exception){
            Log.e(Const.APP_LOGS,"Error User id : $uid  ${e.message}")
            null
        }
    }

    suspend fun checkUserExists(email: String): Boolean {
        return try {
            val querySnapshot = userCollection.whereEqualTo("email",email).get().await()
            Log.d(Const.APP_LOGS, "checking User EXIST: "+querySnapshot.documents.isNotEmpty() )
            querySnapshot.documents.isNotEmpty()
        } catch (e: Exception) {
            Log.e(Const.APP_LOGS, "Error checking User Exist: ${e.message} " )

            false
        }
    }

}