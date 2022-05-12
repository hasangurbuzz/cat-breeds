package com.hasangurbuz.breeds.navigation

sealed class Screen(val route: String) {
    object HomeScreen: Screen("breed_home_screen")
    object BreedFavoriteScreen: Screen("breed_fav_screen")
    object BreedListScreen : Screen("breed_list_screen")
    object BreedDetailScreen : Screen("breed_detail_screen")
    object BreedSearchScreen : Screen("breed_search_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { argument ->
                append("/$argument")
            }
        }
    }
}
