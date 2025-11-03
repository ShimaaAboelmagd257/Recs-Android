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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mini_netflix.R
import com.example.recs.presentation.account.profile.ProfileView
import com.example.recs.presentation.genre.CardGrid
import com.example.recs.presentation.genre.genreCardsContent
import com.example.recs.presentation.home.HomeView
import com.example.recs.presentation.rating.RateView
import com.example.recs.presentation.recommendation.RecommendationView

@Composable
fun HomeTabs(modifier: Modifier = Modifier) {
    val homeTab = TabBarItem(
        title = NavigationRoute.Home.route,
        selectedIcon = painterResource(R.drawable.ic_launcher_background),
        unselectedIcon = painterResource(R.drawable.ic_launcher_foreground),
    )
    val genreTab = TabBarItem(
        title = NavigationRoute.Genre.route,
        selectedIcon = painterResource(R.drawable.ic_launcher_background),
        unselectedIcon = painterResource(R.drawable.ic_launcher_foreground),
    )
    val recsTab = TabBarItem(
        title = NavigationRoute.Recommendation.route,
        selectedIcon = painterResource(R.drawable.ic_launcher_background),
        unselectedIcon = painterResource(R.drawable.ic_launcher_foreground),
    )
    val rateTab = TabBarItem(
        title = NavigationRoute.Rate.route,
        selectedIcon = painterResource(R.drawable.ic_launcher_background),
        unselectedIcon = painterResource(R.drawable.ic_launcher_foreground),
    )

    val profileTab = TabBarItem(
        title = NavigationRoute.Profile.route,
        selectedIcon = painterResource(R.drawable.ic_launcher_background),
        unselectedIcon = painterResource(R.drawable.ic_launcher_foreground),
    )

    val tabBarItems = listOf(homeTab, genreTab,recsTab ,rateTab, profileTab)
    val navController = rememberNavController()

    Surface (
        modifier = Modifier.fillMaxSize()
    ){
        Scaffold(
            bottomBar = { TabView(tabBarItems,navController) },
            modifier = Modifier.background(Color.Transparent),
            content = { it ->
                Box (Modifier.padding(it)) {
                    NavHost(
                        navController = navController,
                        startDestination = homeTab.title,
                    ){
                        composable(NavigationRoute.Home.route) {
                            HomeView (onMovieClicked = { navController.navigate(NavigationRoute.Rate.route) })
                        }
                        composable(NavigationRoute.Genre.route) {
                            CardGrid(cards = genreCardsContent(), columns = 2)
                        }
                        composable(NavigationRoute.Recommendation.route) {
                           RecommendationView()
                        }
                        composable(NavigationRoute.Rate.route) {
                           RateView()
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
    var selectedTabIndex by remember { mutableStateOf(0) }

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
