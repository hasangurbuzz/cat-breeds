package com.hasangurbuz.breeds.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.hasangurbuz.breeds.data.repository.BreedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class BreedListViewModel @Inject constructor(private val breedRepository: BreedRepository) :
    ViewModel() {

    fun changeFavoriteStatus(breedId: String) {
        viewModelScope.launch {
            breedRepository.changeBreedFavoriteStatus(breedId)
        }
    }

}