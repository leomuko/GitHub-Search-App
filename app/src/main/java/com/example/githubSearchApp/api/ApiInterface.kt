package com.example.githubSearchApp.api

import com.example.githubSearchApp.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    @GET("/search/users")
    @Headers("Accept: application/json")
    suspend fun searchApi(
        @Query("q") searchString: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("sort") sortField : String = "login",
        @Query("order") orderBy : String = "asc"
    ): SearchResponse
}