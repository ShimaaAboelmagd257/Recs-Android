package com.example.recs.presentation.splash

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*

import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.sp
import com.example.recs.utility.Const

@Composable
fun WelcomeView(
    onSignInClick:()->Unit,
    onSignUpClick:()->Unit
) {
   Box (modifier = Modifier.fillMaxSize()){
    Column (
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Already have an account? ",
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)

        )

        Button(
            onClick = {
                onSignInClick()
              //  Log.d(Const.APP_LOGS,"onSignInClicked ... ")
                      },
            modifier = Modifier.padding(bottom = 6.dp).width(250.dp),
            colors = ButtonDefaults.buttonColors(Color.Black)
        ) {
            Text(
                text = "Sign In",
                color = Color.White
            )
        }
        Button(
            onClick = {onSignUpClick()
                Log.d(Const.APP_LOGS,"onSignUpClicked ... ")
            },
            modifier = Modifier.padding(bottom = 6.dp).width(250.dp),
            colors = ButtonDefaults.buttonColors(Color.Black)
        ) {
            Text(
                text = "Get Started",
                color = Color.White
            )
        }
    }
   }
}