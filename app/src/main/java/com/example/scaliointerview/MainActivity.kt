package com.example.scaliointerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scaliointerview.adapter.PagingSearchAdapter
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





    fun hideKeyboard(view: View) =
        (applicationContext.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as? InputMethodManager)!!
            .hideSoftInputFromWindow(view.windowToken, 0)
}