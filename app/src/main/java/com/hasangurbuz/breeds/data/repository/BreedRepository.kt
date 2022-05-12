package com.hasangurbuz.breeds.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hasangurbuz.breeds.data.local.BreedDatabase
import com.hasangurbuz.breeds.data.paging.BreedRemoteMediator
import com.hasangurbuz.breeds.data.remote.BreedApi
import com.hasangurbuz.breeds.domain.model.Breed
import com.hasangurbuz.breeds.domain.model.FavBreed
import com.hasangurbuz.breeds.util.Constants.ITEM_LIMIT_PER_PAGE
import com.hasangurbuz.breeds.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
@ActivityScoped
class BreedRepository @Inject constructor(
    private val breedApi: BreedApi,
    private val breedDatabase: BreedDatabase
) {
    fun getAllBreeds(): Flow<PagingData<Breed>> {
        val pagingSourceFactory = { breedDatabase.breedDao.getAllBreeds() }
        return Pager(
            config = PagingConfig(pageSize = ITEM_LIMIT_PER_PAGE),
            remoteMediator = BreedRemoteMediator(
                breedApi = breedApi,
                breedDatabase = breedDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    suspend fun getBreedByName(name: String): Resource<List<Breed>> {
        val searchedBreeds =
            try {
                breedApi.getAllBreedsByName(name)
            } catch (e: Exception) {
                return Resource.Error("Network Error")
            }
        return Resource.Success(data = searchedBreeds)
    }

    suspend fun getBreedById(id: String): List<Breed> {
        return breedDatabase.breedDao.getBreedById(id)
    }

    suspend fun changeBreedFavoriteStatus(breedId: String) {
        breedDatabase.breedDao.changeBreedFavoriteStatus(breedId)
    }

    suspend fun getFavBreeds(): List<Breed> {
        return breedDatabase.breedDao.getFavoriteBreeds()
    }

    suspend fun addToFavBreeds(favList: List<FavBreed>) {
        breedDatabase.breedFavDao.addToFavorites(favList)
    }

    suspend fun removeAllFromFavBreeds() {
        breedDatabase.breedFavDao.removeAllFromFavorites()
    }


}