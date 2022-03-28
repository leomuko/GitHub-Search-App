package com.example.githubSearchApp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.githubSearchApp.model.SearchModel

class MainViewModel(private val repository: Repository) : ViewModel() {
    //val searchResponse1 : MutableLiveData<SearchResponse> = MutableLiveData()
    var searchResponse : LiveData<PagingData<SearchModel>> = MutableLiveData()

   /* fun doApiSearch1(searchString: String){
        viewModelScope.launch {
            val response = repository.searchApi(searchString)
            searchResponse1.value = response
        }
    }*/

    fun doApiSearch(searchString : String){
        val items = repository.getSearchResults(searchString)
        searchResponse = items
        Log.d("Main", "doApiSearch: ${searchResponse.value}")
    }

}