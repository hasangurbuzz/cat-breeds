package com.hasangurbuz.breeds.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hasangurbuz.breeds.util.Constants.BREED_REMOTE_KEY_TABLE

@Entity(tableName = BREED_REMOTE_KEY_TABLE)
data class BreedRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val nextPage: Int?,
    val prevPage: Int?

)