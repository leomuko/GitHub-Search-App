package com.example.githubSearchApp

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.githubSearchApp.api.ApiPagingSource
import com.example.githubSearchApp.model.SearchModel

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