package com.hasangurbuz.breeds.presentation.detail

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
class BreedDetailViewModel @Inject constructor(private val breedRepository: BreedRepository) :
    ViewModel() {

    private val _breedDetail = MutableStateFlow(emptyList<Breed>())


    var breedDetail = _breedDetail

    fun getBreedById(id: String) {
        viewModelScope.launch {
            _breedDetail.value = breedRepository.getBreedById(id)
        }
    }

    fun changeFavoriteStatus(breedId: String) {
        viewModelScope.launch {
            breedRepository.changeBreedFavoriteStatus(breedId)
            getBreedById(breedId)
        }
    }


}