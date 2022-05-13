package com.hasangurbuz.breeds.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.ExperimentalPagingApi
import coil.compose.AsyncImage
import com.hasangurbuz.breeds.presentation.component.TopBar


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

    val breedDetail by viewModel.breedDetail.collectAsState()

    var breedName by remember {
        mutableStateOf("")
    }
    var breedFavState by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopBar(title = breedName, icon = Icons.Default.ArrowBack, navController = navController)
        },
        floatingActionButton = {
            IconButton(onClick = {
                if (breedId != null) {
                    viewModel.changeFavoriteStatus(breedId)
                }
            }) {
                Icon(
                    if (breedFavState) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                    "",
                    tint = Color.Red
                )
            }
        }

    ) {
        LazyColumn() {
            if (breedDetail.isEmpty()) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )
                }
            }
            items(breedDetail) { breed ->
                breedName = breed.name.toString()
                breedFavState = breed.favorite


                Column(modifier = Modifier.padding(horizontal = 20.dp)) {

                    AsyncImage(
                        model = breed.image?.url,
                        contentDescription = breed.description,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(2f)
                    )

                    breed.apply {
                        description?.let { it ->
                            BreedFeature(
                                featureName = "",
                                featureValue = it
                            )
                        }
                        origin?.let { it ->
                            BreedFeature(
                                featureName = "Origin",
                                featureValue = it
                            )
                        }
                        lifeSpan?.let { it ->
                            BreedFeature(
                                featureName = "Life Span",
                                featureValue = it
                            )
                        }

                        wikipediaUrl?.let { it ->
                            BreedFeature(
                                featureName = "Wikipedia Url",
                                featureValue = it,
                                clickable = true
                            )

                        }
                        dogFriendly?.let { it ->
                            BreedFeature(
                                featureName = "Dog Friendly",
                                featureValue = it.toString()
                            )
                        }
                    }

                }

            }
        }

    }


}


@Composable
fun BreedFeature(featureName: String, featureValue: String, clickable: Boolean = false) {
    Column {
        Divider()

        Row(modifier = Modifier.padding(5.dp)) {
            Text(
                text = "$featureName->",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight(600),
                    fontSize = 18.sp
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            val uriHandler = LocalUriHandler.current
            Text(
                text = featureValue,
                textAlign = TextAlign.Left,
                style = TextStyle(
                    color = if (clickable) Color.Blue else Color.Black,
                    fontSize = 17.sp
                ),
                modifier =
                if (clickable) Modifier.clickable { uriHandler.openUri(featureValue) } else {
                    Modifier.padding(1.dp)
                })

        }
    }
}

@Composable
fun Divider() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .height(2.dp)
            .padding(2.dp)
    )
}