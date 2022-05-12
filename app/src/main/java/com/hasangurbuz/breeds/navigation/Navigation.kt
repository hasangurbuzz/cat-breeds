package com.hasangurbuz.breeds.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.ExperimentalPagingApi
import com.hasangurbuz.breeds.presentation.detail.BreedDetailScreen
import com.hasangurbuz.breeds.presentation.favorite.BreedFavoriteScreen
import com.hasangurbuz.breeds.presentation.home.HomeScreen
import com.hasangurbuz.breeds.presentation.search.BreedSearchScreen

@ExperimentalPagingApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.BreedDetailScreen.route + "/{breedId}",
            arguments = listOf(
                navArgument("breedId") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            BreedDetailScreen(
                breedId = entry.arguments?.getString("breedId"),
                navController = navController
            )

        }
        composable(Screen.BreedSearchScreen.route) {
            BreedSearchScreen(navController = navController)
        }
        composable(Screen.BreedFavoriteScreen.route) {
            BreedFavoriteScreen(navController = navController)
        }

    }


}