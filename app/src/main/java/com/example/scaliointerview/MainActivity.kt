package com.example.scaliointerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.scaliointerview.utilities.InternetDetection
import kotlinx.android.synthetic.main.activity_main.*

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
        viewModel.searchResponse.observe(this, Observer { it ->
            if (it.isSuccessful) {
                Toast.makeText(this@MainActivity, "Request Successfully", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "doSearch: ${it.body()}")
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Sorry, Error While Making Request",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d(TAG, "doSearch: ${it.errorBody()}")
                Log.d(TAG, "doSearch: ${it.code()}")
            }
        })


    }
    }
}