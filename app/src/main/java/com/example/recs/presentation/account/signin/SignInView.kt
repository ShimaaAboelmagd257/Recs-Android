package com.example.recs.presentation.account.signin

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.recs.R
import com.example.recs.utility.Const

@Composable
fun SignInView(
    signInViewModel: SignInViewModel = hiltViewModel(),
    onSignInClick:()->Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val viewState by signInViewModel.state.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(viewState) {
        when (val state = viewState) {


            is SignInState.Success -> {
                Toast.makeText(context, "Signup successfully ", Toast.LENGTH_SHORT)
                    .show()
               onSignInClick()

            }

            is SignInState.Error -> {
                Toast.makeText(context, "Please try again later", Toast.LENGTH_SHORT)
                    .show()
                Log.e(Const.APP_LOGS, "Error ${state.errorMessage}")

            }
            is SignInState.WrongPassword -> {
                Toast.makeText(context, "Wrong Password", Toast.LENGTH_SHORT).show()
            }
            is SignInState.UserNotFound ->{
                Toast.makeText(context, "User not Found", Toast.LENGTH_SHORT).show()

            }
            else -> {

            }

        }
    }
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(painter = painterResource(R.drawable.welcome_logo),
            contentDescription = " ",
            modifier = Modifier.size(500.dp).padding(top = 25.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = {email = it},
            label = {
                Text(
                    text = "Enter your Email: ",
                    color = Color.LightGray
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Black,
                unfocusedContainerColor = Color.Gray,
                focusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.White
            )

        )
        Spacer(
            modifier =Modifier.height(20.dp)
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(
                    text = "Enter your Password: ",
                    color = Color.LightGray
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Black,
                unfocusedContainerColor = Color.Gray,
               focusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.White
        )

        )
        Spacer(
            modifier =Modifier.height(20.dp)
        )

        Button(
            colors = ButtonDefaults.buttonColors(Color.Black),
            onClick = {
                if (Const.isValidEmail(email) && Const.isValidPassword(password)) {
                    signInViewModel.signIn(email = email, password = password)
                    Log.d(Const.APP_LOGS, "onSignInClicked")

                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Sign In",
                color = Color.White
            )
            if (viewState is SignInState.Loading) {
                CircularProgressIndicator()
            }
        }






    }


}