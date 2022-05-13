package com.hasangurbuz.breeds.presentation.home

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.hasangurbuz.breeds.data.repository.BreedRepository
import com.hasangurbuz.breeds.domain.model.Breed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val breedRepository: BreedRepository
) : ViewModel() {

    private var _breedData: Flow<PagingData<Breed>> = flowOf()
    val breedData = breedRepository.getAllBreeds()

    init {


    }

}