package com.example.recs.presentation.account.signup

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.recs.utility.Const


@Composable
fun SignUpView(
    signUpViewModel: SignUpViewModel = hiltViewModel(),
    onSignUpClick:()->Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val context = LocalContext.current
    val viewState by signUpViewModel.viewState.collectAsState()
    LaunchedEffect(viewState) {
        when (val state = viewState) {


            is SignUpState.Success -> {
                Toast.makeText(context, "Signup successfully ", Toast.LENGTH_SHORT)
                    .show()
                onSignUpClick()

            }

            is SignUpState.Error -> {
                Toast.makeText(context, "Please try again later", Toast.LENGTH_SHORT)
                    .show()
                Log.e(Const.APP_LOGS, "Error ${state.error}")

            }

            else -> {

            }

        }
    }

    Column (
        modifier = Modifier.padding(16.dp).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = name,
            onValueChange = {name = it},
            label = {
                Text(
                    text = "Your name: ",
                    color = Color.White
                )
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = email,
            onValueChange = {email = it},
            label = {
                Text(
                    text = "Email: ",
                    color = Color.White
                )
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = password,
            onValueChange = {password = it},
            label = {
                Text(
                    text = "Password: ",
                    color = Color.White
                )
            },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = confirmPassword,
            onValueChange = {confirmPassword = it},
            label = {
                Text(
                    text = "Confirm your password: ",
                    color = Color.White
                )
            },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            colors = ButtonDefaults.buttonColors(Color.Black),
            onClick = {
                val intent = SignUpIntent(name, email, password, confirmPassword)
                signUpViewModel.processSignUp(intent)
            }
        ) {
            Text(
                text = "Sign Up",
                color = Color.White
            )
            if (viewState is SignUpState.Loading) {
                CircularProgressIndicator()
            }
        }



    }



}