package com.example.recs.utility.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recs.data.sharedprefrence.SharedPreferences
import com.example.recs.presentation.account.signin.SignInView
import com.example.recs.presentation.account.signup.SignUpView
import com.example.recs.presentation.splash.SplashView
import com.example.recs.presentation.splash.WelcomeView
import com.example.recs.utility.Const
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App:Application(){
}


@Composable
fun MyApp(){
     MyAppContent()
}
val LocalSharedPreferences = compositionLocalOf<SharedPreferences> { error("No SharedPreferences found!") }

@Composable
fun MyAppContent() {
    val navController =  rememberNavController()
    ProvideSharedPreferences {
        val sharedPreferences = LocalSharedPreferences.current
       // val savedSignIn = sharedPreferences.getBoolean(Const.SAVED_SIGN_IN, false)
        val userId = sharedPreferences.getString(Const.USER_ID,"User_Id")

        NavHost(navController = navController, startDestination = NavigationRoute.Splash.route) {
            composable(NavigationRoute.Splash.route) {
                SplashView(
                    navController = navController
                )
            }
            composable(NavigationRoute.WelcomeView.route) {
                WelcomeView(
                    onSignInClick = { navController.navigate(NavigationRoute.SignIn.route) },
                    onSignUpClick = { navController.navigate(NavigationRoute.SignUp.route) }
                )
            }
            composable(NavigationRoute.SignIn.route) {
                SignInView(onSignInClick = { navController.navigate(NavigationRoute.MainTabs.route) })
            }
            composable(NavigationRoute.SignUp.route) {
                SignUpView(onSignUpClick = { navController.navigate(NavigationRoute.MainTabs.route) })
            }
            composable(NavigationRoute.MainTabs.route) {
                HomeTabs(uid = userId)
            }


        }
    }
}


