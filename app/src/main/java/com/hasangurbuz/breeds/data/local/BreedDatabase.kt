package com.hasangurbuz.breeds.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hasangurbuz.breeds.domain.model.Breed
import com.hasangurbuz.breeds.domain.model.BreedRemoteKey
import com.hasangurbuz.breeds.domain.model.FavBreed

@Database(
    entities = [Breed::class, BreedRemoteKey::class, FavBreed::class],
    version = 1
)
abstract class BreedDatabase : RoomDatabase() {
    abstract val breedDao: BreedDao
    abstract val breedRemoteKeyDao: BreedRemoteKeyDao
    abstract val breedFavDao: BreedFavDao

    companion object {
        const val DATABASE_NAME = "breed_database"
    }


}