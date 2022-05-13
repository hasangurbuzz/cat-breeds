package com.hasangurbuz.breeds.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.hasangurbuz.breeds.domain.model.Breed
import com.hasangurbuz.breeds.navigation.Screen

@Composable
fun BreedListElement(
    navController: NavController,
    breed: Breed,
    onFavoriteChange: (String) -> Unit
) {


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(10.dp)
            .clickable {
                navController.navigate(Screen.BreedDetailScreen.withArgs(breed.id))
            }
    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .border(2.dp, Color.Black, RoundedCornerShape(10))
                .padding(10.dp)
        ) {
            AsyncImage(
                model = breed.image?.url, contentDescription = breed.name,
                modifier = Modifier
//                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .height(50.dp)
                    .width(50.dp)
            )
            Text(text = breed.name!!)

            IconButton(onClick = { onFavoriteChange(breed.id) }) {
                Icon(
                    if (breed.favorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    "",
                    tint = Color.Red
                )
            }

        }
    }

}
