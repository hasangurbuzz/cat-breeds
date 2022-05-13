package com.hasangurbuz.breeds.presentation.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.hasangurbuz.breeds.R
import com.hasangurbuz.breeds.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun BreedSplashScreen(navController: NavController) {
    var startAnim by remember {
        mutableStateOf(false)
    }
    val animAlpha = animateFloatAsState(
        targetValue = if (startAnim) 1f else 0f,
        animationSpec = tween(durationMillis = 3000)
    )
    LaunchedEffect(key1 = true) {
        startAnim = true
        delay(4000)
        navController.navigate(Screen.HomeScreen.route) {
            popUpTo(Screen.BreedSplashScreen.route) {
                inclusive = true
            }
        }
    }
    Splash(animAlpha.value)
}


@Composable
fun Splash(alpha: Float) {
    Box(
        modifier = Modifier
            .background(Color(240, 240, 240))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.cat),
            contentDescription = "",
            modifier = Modifier.alpha(alpha)
        )
    }
}