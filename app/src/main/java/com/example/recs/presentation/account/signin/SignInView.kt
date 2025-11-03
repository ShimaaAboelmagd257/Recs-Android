package com.example.recs.presentation.account.signin

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.recs.utility.Const

@Composable
fun SignInView(
    onSignInClick:()->Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = "",
            onValueChange = {},
            label = {
                Text(
                    text = "Enter your Email: ",
                    color = Color.White
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)

        )
        Spacer(
            modifier =Modifier.height(20.dp)
        )
        TextField(
            value = "",
            onValueChange = {

            },
            label = {
                Text(
                    text = "Enter your Password: ",
                    color = Color.White
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)

        )
        Button(
            colors = ButtonDefaults.buttonColors(Color.Black),
            onClick = {
                onSignInClick()
                Log.d(Const.APP_LOGS,"onSignInClicked")
            }
        ) {
            Text(
                text = "Sign In",
                color = Color.White
            )
        }


    }


}