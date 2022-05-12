package com.hasangurbuz.breeds.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hasangurbuz.breeds.domain.model.FavBreed

@Dao
interface BreedFavDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(favList: List<FavBreed>)

    @Query("SELECT * FROM BREED_FAV_TABLE")
    suspend fun getFavoriteBreeds(): List<FavBreed>

    @Query("DELETE FROM BREED_FAV_TABLE")
    suspend fun removeAllFromFavorites()

}