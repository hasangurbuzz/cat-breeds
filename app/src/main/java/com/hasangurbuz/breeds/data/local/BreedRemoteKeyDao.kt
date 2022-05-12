package com.hasangurbuz.breeds.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hasangurbuz.breeds.domain.model.BreedRemoteKey


@Dao
interface BreedRemoteKeyDao {

    @Query("SELECT * FROM BREED_REMOTE_KEY_TABLE WHERE ID= :id")
    suspend fun getAllRemoteKeys(id: String): BreedRemoteKey

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRemoteKeys(remoteKey: List<BreedRemoteKey>)

    @Query("DELETE FROM BREED_REMOTE_KEY_TABLE")
    suspend fun deleteAllRemoteKey()
}