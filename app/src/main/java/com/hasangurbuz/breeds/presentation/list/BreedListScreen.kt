package com.hasangurbuz.breeds.presentation.list


import android.widget.ProgressBar
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
            } else if (breedItems.loadState.refresh is LoadState.Loading) {
                item {
                    Box(modifier = Modifier.fillParentMaxSize()) {
                        Text(
                            text = "Loading",
                            modifier = Modifier.align(Alignment.Center)
                        )
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


//@Composable
//fun SearchBar(
//    navController: NavController,
//    modifier: Modifier = Modifier,
//    hint: String = "Search Cat Breeds!",
//    onSearch: (String) -> Unit = {}
//) {
//    val text = remember {
//        mutableStateOf("")
//    }
//    val hintState = remember {
//        mutableStateOf(true)
//    }
//
//    Box() {
//        OutlinedTextField(
//            value = text.value,
//            onValueChange = { it ->
//                text.value = it
//                onSearch(it)
//            },
//            maxLines = 1,
//            singleLine = true,
//            textStyle = TextStyle(color = Color.Black),
//            modifier = Modifier
//                .fillMaxWidth()
////                .shadow(5.dp, RoundedCornerShape(10))
////                .border(2.dp,MaterialTheme.colors.primary)
////                .background(Color.White, RoundedCornerShape(10))
//                .padding(horizontal = 10.dp, vertical = 12.dp)
//                .onFocusChanged {
//                    hintState.value = it.isFocused && text.value.isEmpty()
//                }
//
//        )
//        if (!hintState.value) {
//            Text(
//                text = hint,
//                color = Color.LightGray,
//                modifier = Modifier
//                    .padding(horizontal = 30.dp, vertical = 30.dp)
//            )
//        }
//    }
//}

