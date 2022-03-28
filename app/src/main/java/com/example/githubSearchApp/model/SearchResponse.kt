package com.example.githubSearchApp.model

data class SearchResponse(
    val total_count : Int,
    val incomplete_results : Boolean,
    val items : List<SearchModel>
)
