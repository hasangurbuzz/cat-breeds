package com.hasangurbuz.breeds.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import com.hasangurbuz.breeds.navigation.Screen
import com.hasangurbuz.breeds.presentation.list.BreedListScreen
import com.hasangurbuz.breeds.presentation.list.SearchBar

@ExperimentalPagingApi
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val breedItems = viewModel.breedData.collectAsLazyPagingItems()
    breedItems.refresh()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Cat Breeds",
                        color = MaterialTheme.colors.primary
                    )
                },
                backgroundColor = MaterialTheme.colors.background,
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.BreedFavoriteScreen.route) }) {
                        Icon(
                            Icons.Rounded.Favorite,
                            "",
                            tint = Color.Red
                        )
                    }
                }
            )
        }
    ) {


        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize()
        ) {

            Column {
                SearchBar(navController = navController)
                BreedListScreen(navController = navController, breedItems = breedItems)


            }
        }
    }
}