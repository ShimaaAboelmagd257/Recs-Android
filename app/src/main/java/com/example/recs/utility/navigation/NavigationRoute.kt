package com.example.recs.utility.navigation

sealed class NavigationRoute (val route:String){

    object Splash: NavigationRoute("splash")
    object WelcomeView: NavigationRoute("welcomeView")

    object Profile: NavigationRoute("profile")
    object SignIn: NavigationRoute("signIn")
    object SignUp: NavigationRoute("signUp")
    object MainTabs: NavigationRoute("MainTabs")


    object Home: NavigationRoute("Home")
    object MovieCard: NavigationRoute("movieCard")
    object Genre: NavigationRoute("Genre")
    object Recommendation: NavigationRoute("Recs")
    object Rate: NavigationRoute("Rate")
    object CardGrid: NavigationRoute("cardGrid")

}