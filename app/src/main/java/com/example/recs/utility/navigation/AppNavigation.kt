package com.example.recs.utility.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recs.presentation.account.signin.SignInView
import com.example.recs.presentation.account.signup.SignUpView
import com.example.recs.presentation.splash.SplashView
import com.example.recs.presentation.splash.WelcomeView
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App:Application(){
}


@Composable
fun MyApp(){
     MyAppContent()
}

@Composable
fun MyAppContent() {
    val navController =  rememberNavController()
    NavHost(navController = navController, startDestination = NavigationRoute.Splash.route ){
        composable(NavigationRoute.Splash.route) {
            SplashView(
                navController = navController
               // onSplashFinished = { navController.navigate(NavigationRoute.WelcomeView.route)}
            )
        }
        composable(NavigationRoute.WelcomeView.route) {
            WelcomeView(
                onSignInClick = {navController.navigate(NavigationRoute.SignIn.route)},
                onSignUpClick = {navController.navigate(NavigationRoute.SignUp.route)}
            )
        }
        composable(NavigationRoute.SignIn.route) {
            SignInView (onSignInClick = {navController.navigate(NavigationRoute.MainTabs.route)})
        }
        composable(NavigationRoute.SignUp.route) {
            SignUpView(onSignUpClick = {navController.navigate(NavigationRoute.MainTabs.route)})
        }
        composable (NavigationRoute.MainTabs.route) {
             HomeTabs()
        }


    }
}


