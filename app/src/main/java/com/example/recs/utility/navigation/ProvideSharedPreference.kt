package com.example.recs.utility.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.recs.data.sharedprefrence.SharedPreferences

@Composable
fun ProvideSharedPreferences(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val sharedPreferences = remember {
        SharedPreferences(context)
    }
    CompositionLocalProvider(LocalSharedPreferences provides sharedPreferences) {
        content()
    }
}
