package com.example.scaliointerview

import com.example.scaliointerview.api.RetrofitInstance
import com.google.gson.JsonElement
import retrofit2.Response

class Repository {

    suspend fun searchApi(searchString: String) : Response<JsonElement>{
        return RetrofitInstance.apiInterface.searchApi(searchString)
    }
}