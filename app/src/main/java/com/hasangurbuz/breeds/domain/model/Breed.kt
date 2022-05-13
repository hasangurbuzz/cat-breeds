package com.hasangurbuz.breeds.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.hasangurbuz.breeds.util.Constants.BREED_TABLE

@Entity(tableName = BREED_TABLE)
data class Breed(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val adaptability: Int?,
    val name: String?,
    val description: String?,
    @Embedded
    val image: Image?,
    val origin: String?,
    @SerializedName("life_span")
    val lifeSpan: String?,
    @SerializedName("dog_friendly")
    val dogFriendly: Int?,
    @SerializedName("wikipedia_url")
    val wikipediaUrl: String?,
    val favorite: Boolean = false
)
