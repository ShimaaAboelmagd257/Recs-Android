package com.example.recs.presentation.splash

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.mini_netflix.R
import com.example.recs.utility.Const
import com.example.recs.utility.navigation.NavigationRoute
import kotlinx.coroutines.delay

@Composable
fun SplashView(
     navController: NavController
) {
    val preloaderLottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.videoediting))
    val preloadProgress by animateLottieCompositionAsState(
       composition =  preloaderLottieComposition,
        iterations = 6
    )
    LaunchedEffect (preloaderLottieComposition) {
        Log.d(Const.APP_LOGS,"Loading composition $preloaderLottieComposition ")
        delay(2000)
        navController.navigate(NavigationRoute.WelcomeView.route)

    }
    Column (
        modifier = Modifier.fillMaxSize().background(Color.Red),
         verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if(preloaderLottieComposition != null) {
            LottieAnimation(
                composition = preloaderLottieComposition,
                progress = preloadProgress,
                modifier = Modifier.size(200.dp)
            )
        }else{
            Log.e(Const.APP_LOGS,"error in splash screen Lottie composition is null")
        }

       /* Text(
            text = "Recommendation RECS",
            color = Color.Black,
            fontSize = 50.sp,
           // modifier = Modifier.padding(bottom = 80.dp)
        )*/



    }


}