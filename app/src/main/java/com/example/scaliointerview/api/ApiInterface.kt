package com.example.scaliointerview.api

import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    @GET("/search/users")
    @Headers("Accept: application/json")
    suspend fun searchApi(
        @Query("q") searchString: String
    ) : Response<JsonElement>
}