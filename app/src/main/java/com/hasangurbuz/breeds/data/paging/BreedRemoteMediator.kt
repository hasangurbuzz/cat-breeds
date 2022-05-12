package com.hasangurbuz.breeds.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.hasangurbuz.breeds.data.local.BreedDatabase
import com.hasangurbuz.breeds.data.remote.BreedApi
import com.hasangurbuz.breeds.domain.model.Breed
import com.hasangurbuz.breeds.domain.model.BreedRemoteKey
import com.hasangurbuz.breeds.util.Constants.ITEM_LIMIT_PER_PAGE
import com.hasangurbuz.breeds.util.mapper.BreedMapper

@ExperimentalPagingApi
class BreedRemoteMediator(
    private val breedApi: BreedApi,
    private val breedDatabase: BreedDatabase
) : RemoteMediator<Int, Breed>() {
    private val breedDao = breedDatabase.breedDao
    private val breedRemoteKeyDao = breedDatabase.breedRemoteKeyDao
    private val breedFavDao = breedDatabase.breedFavDao

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Breed>): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 0
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }
            val response = breedApi.getAllBreeds(page = currentPage, limit = ITEM_LIMIT_PER_PAGE)
            val endOfPaginationReached = response.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            breedDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    breedFavDao.addToFavorites(
                        breedDao.getFavoriteBreeds()
                            .map { x -> BreedMapper().toEntity(x) })
                    breedDao.deleteAllBreeds()
                    breedRemoteKeyDao.deleteAllRemoteKey()
                }
                val keys = response.map { breed ->
                    BreedRemoteKey(
                        id = breed.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                breedRemoteKeyDao.addRemoteKeys(remoteKey = keys)
                breedDao.addBreeds(breedList = response)
                breedDao.addBreeds(
                    breedFavDao.getFavoriteBreeds().map { x -> BreedMapper().fromEntity(x) }
                )
                breedFavDao.removeAllFromFavorites()
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Breed>
    ): BreedRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                breedRemoteKeyDao.getAllRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Breed>
    ): BreedRemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { breed ->
                breedRemoteKeyDao.getAllRemoteKeys(id = breed.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Breed>
    ): BreedRemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { breed ->
                breedRemoteKeyDao.getAllRemoteKeys(id = breed.id)
            }
    }

}

