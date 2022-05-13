package com.hasangurbuz.breeds.data.local

import androidx.paging.PagingSource
import androidx.room.*
import com.hasangurbuz.breeds.domain.model.Breed

@Dao
interface BreedDao {


    @Query("SELECT * FROM BREED_TABLE ORDER BY name ASC")
    fun getAllBreeds(): PagingSource<Int, Breed>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBreeds(breedList: List<Breed>)

    @Query("SELECT * FROM BREED_TABLE WHERE ID = :id")
    suspend fun getBreedById(id: String): List<Breed>

    @Query("SELECT * FROM BREED_TABLE WHERE FAVORITE = 1 ORDER BY NAME ASC")
    suspend fun getFavoriteBreeds(): List<Breed>

    //
    @Query("UPDATE BREED_TABLE SET FAVORITE = NOT FAVORITE WHERE ID= :breedId ")
    suspend fun changeBreedFavoriteStatus(breedId: String)

    @Delete
    suspend fun deleteBreed(breed: Breed)

    @Query("DELETE FROM BREED_TABLE")
    suspend fun deleteAllBreeds()
}