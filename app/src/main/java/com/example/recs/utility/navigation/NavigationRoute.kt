package com.example.recs.utility.navigation

sealed class NavigationRoute (val route:String){

    data object Splash: NavigationRoute("splash")
    data object WelcomeView: NavigationRoute("welcomeView")

    data object Profile: NavigationRoute("profile")
    data object SignIn: NavigationRoute("signIn")
    data  object SignUp: NavigationRoute("signUp")
    data object MainTabs: NavigationRoute("MainTabs")


    data object Home: NavigationRoute("Home")
    data object MovieDetails: NavigationRoute("MovieDetails")
     data object MoviesByGenre: NavigationRoute("MoviesByGenre/{genreId}"){
         fun passGenreId(id:Int):String = "MoviesByGenre/$id"
     }
    data object Genre: NavigationRoute("Genre")
    data object Recommendation: NavigationRoute("Recs")
    data object Rate: NavigationRoute("Rates")
    data object CardGrid: NavigationRoute("cardGrid")

}