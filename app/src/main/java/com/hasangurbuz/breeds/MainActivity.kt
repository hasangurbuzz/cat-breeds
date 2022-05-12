package com.hasangurbuz.breeds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.paging.ExperimentalPagingApi
import com.hasangurbuz.breeds.navigation.Navigation
import com.hasangurbuz.breeds.ui.theme.BreedsTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class MainActivity() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {

            BreedsTheme {
                // A surface container using the 'background' color from the theme




                    Navigation()



            }
        }
    }
}

