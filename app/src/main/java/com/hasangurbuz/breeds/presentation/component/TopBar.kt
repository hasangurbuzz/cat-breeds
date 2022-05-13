package com.hasangurbuz.breeds.presentation.component

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController

@Composable
fun TopBar(
    icon: ImageVector,
    title: String,
    navController: NavController
) {
    TopAppBar(
        title = { Text(title) },
        backgroundColor = MaterialTheme.colors.background,
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(icon, contentDescription = "")
            }
        }
    )
}