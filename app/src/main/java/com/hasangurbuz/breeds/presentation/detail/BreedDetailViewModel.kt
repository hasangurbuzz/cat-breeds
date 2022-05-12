package com.hasangurbuz.breeds.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.hasangurbuz.breeds.data.repository.BreedRepository
import com.hasangurbuz.breeds.domain.model.Breed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class BreedDetailViewModel @Inject constructor(private val breedRepository: BreedRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(emptyList<Breed>())
    val state: StateFlow<List<Breed>> = _state


    fun getBreedById(id: String) {
        viewModelScope.launch {
            _state.value = breedRepository.getBreedById(id)
        }
    }


}