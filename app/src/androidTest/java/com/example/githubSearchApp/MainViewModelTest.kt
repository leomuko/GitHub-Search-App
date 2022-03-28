package com.example.githubSearchApp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainViewModelTest : TestCase(){

    private lateinit var viewModel: MainViewModel

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Before
    public override fun setUp() {
        super.setUp()
        val repository = Repository()
        viewModel = MainViewModel(repository)
    }

    @Test
    fun testingApiSearch(){
        viewModel.doApiSearch("bb")
        val result = viewModel.searchResponse.getOrAwaitValue()
        assertNotEquals(result, null)
    }
}