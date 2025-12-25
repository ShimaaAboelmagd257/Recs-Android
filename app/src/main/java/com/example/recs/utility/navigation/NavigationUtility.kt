package com.example.recs.utility.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recs.R
import com.example.recs.presentation.account.profile.ProfileView
import com.example.recs.presentation.genre.GenreView
import com.example.recs.presentation.genre.MoviesByGenreView

import com.example.recs.presentation.home.HomeView
import com.example.recs.presentation.movie.MovieWithDetailsView
import com.example.recs.presentation.rating.RateView
import com.example.recs.presentation.recommendation.RecsView

@Composable
fun HomeTabs() {
    val homeTab = TabBarItem(
        title = NavigationRoute.Home.route,
        selectedIcon = painterResource(R.drawable.home_filled),
        unselectedIcon = painterResource(R.drawable.home_outlined),
    )
    val genreTab = TabBarItem(
        title = NavigationRoute.Genre.route,
        selectedIcon = painterResource(R.drawable.genre_filled),
        unselectedIcon = painterResource(R.drawable.genre_outline),
    )
    val recsTab = TabBarItem(
        title = NavigationRoute.Recommendation.route,
        selectedIcon = painterResource(R.drawable.recs_filles),
        unselectedIcon = painterResource(R.drawable.recs_outline),
    )
    val rateTab = TabBarItem(
        title = NavigationRoute.Rate.route,
        selectedIcon = painterResource(R.drawable.rates_filled),
        unselectedIcon = painterResource(R.drawable.rates_outline),
    )

    val profileTab = TabBarItem(
        title = NavigationRoute.Profile.route,
        selectedIcon = painterResource(R.drawable.account_filled),
        unselectedIcon = painterResource(R.drawable.account_outlined),
    )

    val tabBarItems = listOf(homeTab, genreTab,recsTab ,rateTab, profileTab)
    val navController = rememberNavController()

    Surface (
        modifier = Modifier.fillMaxSize()
    ){
        Scaffold(
            bottomBar = { TabView(tabBarItems,navController) },
            modifier = Modifier.background(Color.Transparent),
            content = {
                Box (Modifier.padding(it)) {
                    NavHost(
                        navController = navController,
                        startDestination = homeTab.title,
                    ){
                        composable(NavigationRoute.Home.route) {
                            HomeView { movieId ->
                                navController.navigate( "movieDetails/$movieId")
                            }                        }
                        composable(NavigationRoute.Genre.route) {
                            GenreView(onGenreClicked = {genreId ->
                                navController.navigate(NavigationRoute.MoviesByGenre.passGenreId(genreId))

                            }

                            )
                        }
                        composable( "movieDetails/{movieId}", arguments = listOf(navArgument("movieId") { type = NavType.IntType }) ) {
                            val movieId = it.arguments?.getInt("movieId")!!
                            MovieWithDetailsView (movieId = movieId , onRateClicked = {})
                        }
                        composable(
                            NavigationRoute.MoviesByGenre.route,
                            arguments = listOf(navArgument("genreId") { type = NavType.IntType })
                        ) { backStackEntry ->

                            val genreId = backStackEntry.arguments?.getInt("genreId") ?: 0

                            MoviesByGenreView(
                                genreId = genreId, onMovieClicked = { }
                            )
                        }
                        composable(NavigationRoute.Recommendation.route) {
                           RecsView(onMovieClicked = { navController.navigate(NavigationRoute.Rate.route) }, userId = 1)
                        }
                        composable(NavigationRoute.Rate.route) {
                           RateView(userId = 1, onMovieClicked = {})
                        }
                        composable(NavigationRoute.Profile.route) {
                            ProfileView()
                        }



                    }
                }
            }


        )

    }

}

@Composable
fun TabBarIconView(
    isSelected: Boolean,
    selectedIcon: Painter,
    unselectedIcon: Painter,
    title: String,
    badgeAmount: Int? = null
) {
    BadgedBox(badge = { TabBarBadgeView(badgeAmount) }) {
        Icon(
            painter = if (isSelected) {selectedIcon} else {unselectedIcon},
            contentDescription = title,
            modifier = Modifier.size(30.dp)
        )
    }
}
@Composable
fun TabBarBadgeView(count: Int? = null) {
    if (count != null) {
        Badge {
            Text(count.toString())
        }
    }
}
data class TabBarItem(
    val selectedIcon: Painter,
    val unselectedIcon: Painter,
    val title: String
)
@Composable
fun TabView(tabBarItems: List<TabBarItem>, navController: NavController) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    NavigationBar(containerColor = Color.Transparent) {
        tabBarItems.forEachIndexed { index, tabBarItem ->
            NavigationBarItem(
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    navController.navigate(tabBarItem.title)
                },
                icon ={
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        TabBarIconView(
                            isSelected = selectedTabIndex == index,
                            selectedIcon = tabBarItem.selectedIcon,
                            unselectedIcon = tabBarItem.unselectedIcon,
                            title = tabBarItem.title
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(tabBarItem.title)
                }
            )
        }
    }
}
