package com.example.recs.presentation.account.profile

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.recs.utility.Const

@Composable
fun ProfileView( viewModel : ProfileViewModel = hiltViewModel() , uid:String) {

    val state by viewModel.state.collectAsState()
    LaunchedEffect (uid){
        viewModel.fetchUserData(uid)
    }
    when (val profileState = state) {
        is ProfileState.Success -> {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                val profileInfo = profileState.user
                Text(text = "Hello, " + profileInfo.name)
                Text(text = profileInfo.email)
                Button(modifier = Modifier.fillMaxWidth(),
                    onClick = {
                    viewModel.signOut()
                }) {
                    Text("Sign out")
                }

            }
        }
        is ProfileState.Error -> {
            Log.e(Const.APP_LOGS, "Error in ProfileView ${profileState.error}")
        }
        ProfileState.Loading -> {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(50.dp),
                    color = Color.Black)
                Log.w(Const.APP_LOGS,"LOADING in Home")
            }
        }
    }

}