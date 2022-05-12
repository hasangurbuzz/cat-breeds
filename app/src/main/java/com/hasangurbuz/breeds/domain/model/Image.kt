package com.hasangurbuz.breeds.domain.model

import com.google.gson.annotations.SerializedName


data class Image(
    @SerializedName("id")
    val imageId: String?,
    val height: Int,
    val width: Int,
    val url: String?
)
