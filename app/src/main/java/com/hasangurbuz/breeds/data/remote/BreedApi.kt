package com.hasangurbuz.breeds.data.remote

import com.hasangurbuz.breeds.domain.model.Breed
import com.hasangurbuz.breeds.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BreedApi {

    @Headers("x-api-key: $API_KEY")
    @GET("breeds")
    suspend fun getAllBreeds(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): List<Breed>

    @Headers("x-api-key: $API_KEY")
    @GET("breeds/search")
    suspend fun getAllBreedsByName(
        @Query("q") name: String
    ): List<Breed>


}