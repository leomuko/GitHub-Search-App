package com.example.scaliointerview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.scaliointerview.api.ApiPagingSource
import com.example.scaliointerview.api.RetrofitInstance
import com.example.scaliointerview.model.SearchModel
import com.example.scaliointerview.model.SearchResponse
import com.google.gson.JsonElement
import retrofit2.Response

class Repository {

   /* suspend fun searchApi(searchString: String) : SearchResponse{
        return RetrofitInstance.apiInterface.searchApi(searchString, 1, 20)
    }*/

  fun getSearchResults(searchString: String): LiveData<PagingData<SearchModel>> {
      return Pager(
          config = PagingConfig(
              pageSize = 9,
              maxSize = 100,
              enablePlaceholders = false
          ),
          pagingSourceFactory = {
              ApiPagingSource(searchString)
          }
      ).liveData
  }

}