package com.hasangurbuz.breeds.presentation.list


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.hasangurbuz.breeds.domain.model.Breed
import com.hasangurbuz.breeds.navigation.Screen
import com.hasangurbuz.breeds.presentation.component.BreedListElement

@ExperimentalPagingApi
@Composable
fun BreedListScreen(
    navController: NavController,
    listViewModel: BreedListViewModel = hiltViewModel(),
    breedItems: LazyPagingItems<Breed>,
) {


    Column() {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (breedItems.loadState.append is LoadState.Loading) {
                item {
                    CircProgressPar()
                }
            } else if (breedItems.loadState.prepend.endOfPaginationReached) {
                breedItems.refresh()
            } else if (breedItems.loadState.refresh is LoadState.Loading) {
                item {
                    Box(modifier = Modifier.fillParentMaxSize()) {
                        CircProgressPar()
                    }
                }
            }
            items(
                items = breedItems,
                key = { breed ->
                    breed.id
                }
            ) { breed ->
                breed?.let {
                    BreedListElement(
                        breed = it,
                        navController = navController,
                        onFavoriteChange = { listViewModel.changeFavoriteStatus(breed.id) }
                    )
                }
            }

        }
        if (breedItems.loadState.append.endOfPaginationReached) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "End of the page",
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }

        }
    }


}


@Composable
fun SearchBar(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 15.dp)
            .background(MaterialTheme.colors.background)
            .clickable { navController.navigate(Screen.BreedSearchScreen.route) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, MaterialTheme.colors.primary, RoundedCornerShape(20))
                .padding(vertical = 15.dp, horizontal = 15.dp)

        ) {
            Text(text = "Search Cat Breeds", color = Color.Gray)

        }
    }

}

@Composable
fun CircProgressPar() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    )
}

