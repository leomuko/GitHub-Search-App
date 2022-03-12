package com.example.scaliointerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scaliointerview.adapter.PagingSearchAdapter
import com.example.scaliointerview.adapter.SearchAdapter
import com.example.scaliointerview.model.SearchModel
import com.example.scaliointerview.utilities.InternetDetection
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    companion object{
        const val TAG = "MainActivity"
    }

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialise ViewModel
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        searchButton.setOnClickListener {
            doSearch()
            hideKeyboard(window.decorView.rootView)
        }
    }

    private fun doSearch() {
        if (!InternetDetection.isInternetAvailable(this)){
            Toast.makeText(
                applicationContext,
                "Please Check Your Internet Connection",
                Toast.LENGTH_SHORT
            ).show()
        }else{
            val searchString = searchEt.text.toString()

            if (searchString.isNullOrEmpty()){
                Toast.makeText(this, "Please Enter a string to search", Toast.LENGTH_SHORT).show()
                return
            }

            viewModel.doApiSearch(searchString)
            viewModel.searchResponse.observe(this, Observer {
                val adapter: PagingSearchAdapter = PagingSearchAdapter(this@MainActivity)
                 adapter.submitData(lifecycle, it)
                search_recycler.adapter = adapter

                val manager = LinearLayoutManager(this)
                search_recycler.layoutManager = manager

            })
        }
    }

     /*private fun doSearch() {
         if (!InternetDetection.isInternetAvailable(this)){
             Toast.makeText(
                 applicationContext,
                 "Please Check Your Internet Connection",
                 Toast.LENGTH_SHORT
             ).show()
         }else{
         val searchString = searchEt.text.toString()

         if (searchString.isNullOrEmpty()){
             Toast.makeText(this, "Please Enter a string to search", Toast.LENGTH_SHORT).show()
             return
         }

         viewModel.doApiSearch1(searchString)
         viewModel.searchResponse1.observe(this, Observer { it ->
             val gg = it.total_count
             Log.d(TAG, "doSearch: ${it.items}")
             *//* if (it.isSuccessful) {
                 Toast.makeText(this@MainActivity, "Request Successfully", Toast.LENGTH_SHORT).show()
                // Log.d(TAG, "doSearch: ${it.body()}")
                 val searchModelsList = ArrayList<SearchModel>()
                 try {
                     val json = JSONObject(it.body().toString())
                     val itemsArray = json.getJSONArray("items")
                     for (i in 0 until itemsArray.length()){
                         val item = itemsArray.getJSONObject(i)
                         val id = item.getInt("id")
                         val profileUrl = item.getString("avatar_url")
                         val login = item.getString("login")
                         val type = item.getString("type")

                         val searchModel : SearchModel = SearchModel(id, profileUrl, login, type)
                         searchModelsList.add(searchModel)
                     }

                     if (searchModelsList.size == 0){
                         Toast.makeText(this@MainActivity, "No Results Were Found", Toast.LENGTH_SHORT).show()
                     }else{
                         initialiseViewModel(searchModelsList)
                     }
                 }catch (e: Exception) {
                     Log.d("Response", "Json Error :" + e.message)
                 }
             } else {
                 Toast.makeText(
                     this@MainActivity,
                     "Sorry, Error While Making Request",
                     Toast.LENGTH_SHORT
                 ).show()
                 Log.d(TAG, "doSearch: ${it.errorBody()}")
                 Log.d(TAG, "doSearch: ${it.code()}")
             }*//*
         })


     }
     }*/

    private fun initialiseViewModel(searchModelsList: java.util.ArrayList<SearchModel>) {
        val adapter : SearchAdapter = SearchAdapter(this, searchModelsList)
        val manager = LinearLayoutManager(this)
        search_recycler.layoutManager = manager
        search_recycler.adapter = adapter
    }

    fun hideKeyboard(view: View) =
        (applicationContext.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as? InputMethodManager)!!
            .hideSoftInputFromWindow(view.windowToken, 0)
}