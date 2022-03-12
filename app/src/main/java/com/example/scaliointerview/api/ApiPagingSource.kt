package com.example.scaliointerview.api

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.example.scaliointerview.model.SearchModel
import com.example.scaliointerview.model.SearchResponse
import org.json.JSONObject
import java.io.IOException
import kotlin.math.log

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class ApiPagingSource(
    private val query: String
) : PagingSource<Int, SearchModel>() {
    override fun getRefreshKey(state: PagingState<Int, SearchModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchModel> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = RetrofitInstance.apiInterface.searchApi(query, position, params.loadSize)
            val results = response.items

            Log.d("Main", "load: " + results.size)

            LoadResult.Page(
                data = results,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (results.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            Log.d("Main", "IOException: ${exception.toString()}")
            LoadResult.Error(exception)

        } catch (exception: HttpException) {
            Log.d("Main", "HttpException: ${exception.toString()}")
            LoadResult.Error(exception)
        }
    }


}


/*
class UnsplashPagingSource(
    private val unsplashApi: UnsplashApi,
    private val query: String
) : PagingSource<Int, UnsplashPhoto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = unsplashApi.searchPhotos(query, position, params.loadSize)
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}*/
