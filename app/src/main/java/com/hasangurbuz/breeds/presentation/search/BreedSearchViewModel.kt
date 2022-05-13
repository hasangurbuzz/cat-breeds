package com.hasangurbuz.breeds.presentation.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.hasangurbuz.breeds.data.repository.BreedRepository
import com.hasangurbuz.breeds.domain.model.Breed
import com.hasangurbuz.breeds.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class BreedSearchViewModel @Inject constructor(private val breedRepository: BreedRepository) :
    ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchedBreeds = MutableStateFlow(emptyList<Breed>())
    val searchedBreeds: StateFlow<List<Breed>> = _searchedBreeds

    fun updateSearchQuery(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun searchBreeds(name: String) {
        viewModelScope.launch {
            val result = breedRepository.getBreedByName(name)
            when (result) {
                is Resource.Success -> {
                    if (result.data != null) {
                        _searchedBreeds.value = result.data
                    }

                }
                is Resource.Error -> {
                    _searchedBreeds.value = listOf()

                }
            }
        }
    }

    fun changeFavoriteStatus(breedId: String) {
        viewModelScope.launch {
            breedRepository.changeBreedFavoriteStatus(breedId)
        }
    }

}