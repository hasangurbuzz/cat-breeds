package com.hasangurbuz.breeds.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.hasangurbuz.breeds.data.repository.BreedRepository
import com.hasangurbuz.breeds.domain.model.Breed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class BreedFavoriteViewModel @Inject constructor(
    private val repository: BreedRepository
) : ViewModel() {

    private val _favBreeds = MutableStateFlow(emptyList<Breed>())
    val favBreeds = _favBreeds

    init {
        updateFavBreeds()
    }

    fun removeFromFavBreeds(breedId: String) {
        viewModelScope.launch {
            repository.changeBreedFavoriteStatus(breedId)
        }
    }

    fun updateFavBreeds() {
        viewModelScope.launch {
            _favBreeds.value = repository.getFavBreeds()
        }
    }


}