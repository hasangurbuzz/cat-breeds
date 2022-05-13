package com.hasangurbuz.breeds.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.hasangurbuz.breeds.navigation.Screen
import com.hasangurbuz.breeds.presentation.list.BreedListScreen
import com.hasangurbuz.breeds.presentation.list.CircProgressPar
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
        },
        floatingActionButton = {
            if (breedItems.loadState.refresh is LoadState.Error) {
                FloatingActionButton(
                    onClick = {
                        breedItems.retry()
                        breedItems.refresh()
                    },
                    backgroundColor = Color.Red
                ) {
                    Icon(
                        Icons.Default.Refresh,
                        contentDescription = "",
                        tint = Color.White
                    )
                }

            }
            if (breedItems.loadState.append is LoadState.Error) {
                FloatingActionButton(
                    onClick = { breedItems.refresh() },
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Icon(
                        Icons.Default.Refresh,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }

        },
        bottomBar = {
            if (breedItems.loadState.refresh is LoadState.Error) {
                BottomAppBar(
                    cutoutShape = MaterialTheme.shapes.small.copy(
                        CornerSize(percent = 50)
                    ), backgroundColor = Color.Red
                ) {
                    Text("Network Error", color = Color.White, textAlign = TextAlign.Center)

                }
            }
        },

        ) {


        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize()
        ) {

            if (breedItems.loadState.refresh is LoadState.Error) CircProgressPar()
            Column {
                SearchBar(navController = navController)
                BreedListScreen(navController = navController, breedItems = breedItems)


            }
        }
    }
}