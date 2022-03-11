package com.example.scaliointerview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonElement
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {
    val searchResponse : MutableLiveData<Response<JsonElement>> = MutableLiveData()

    fun doApiSearch(searchString: String){
        viewModelScope.launch {
            val response = repository.searchApi(searchString)
            searchResponse.value = response
        }
    }
}