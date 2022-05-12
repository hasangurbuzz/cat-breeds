package com.hasangurbuz.breeds.presentation.favorite

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.ExperimentalPagingApi
import com.hasangurbuz.breeds.presentation.component.BreedListElement


@ExperimentalPagingApi
@Composable
fun BreedFavoriteScreen(
    navController: NavController,
    viewModel: BreedFavoriteViewModel = hiltViewModel()
) {
    val favBreeds by viewModel.favBreeds.collectAsState()

    LazyColumn() {
        items(favBreeds) { breed ->
            BreedListElement(
                navController = navController,
                breed = breed,
                onFavoriteChange = { breedId ->
                    viewModel.removeFromFavBreeds(breedId)
                    viewModel.updateFavBreeds()
                })


        }
    }


}

