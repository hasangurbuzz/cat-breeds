package com.hasangurbuz.breeds.presentation.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.ExperimentalPagingApi
import coil.compose.AsyncImage


@ExperimentalPagingApi
@Composable
fun BreedDetailScreen(
    breedId: String?,
    viewModel: BreedDetailViewModel = hiltViewModel(),
    navController: NavController
) {

    if (breedId != null) {
        viewModel.getBreedById(breedId)
    }


    val state by viewModel.state.collectAsState()



    LazyColumn() {
        if (state.isEmpty()) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }
        }
        items(state) { breed ->
            AsyncImage(
                model = breed.image?.url,
                contentDescription = breed.description,
                modifier = Modifier.fillMaxSize()
            )
            Text(text = "converted.name!!")
            BreedFeature(featureName = "Origin", featureValue = breed.origin!!)
            BreedFeature(featureName = "Wiki", featureValue = "Url")
        }
    }


//    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//
//
//    }

}


@Composable
fun BreedFeature(featureName: String, featureValue: String) {
    Row {
        Text(text = featureName)
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = featureValue)
    }
}