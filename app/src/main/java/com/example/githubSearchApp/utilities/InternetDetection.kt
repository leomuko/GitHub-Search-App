package com.example.githubSearchApp.utilities

import android.content.Context
import android.net.ConnectivityManager

object InternetDetection {
    fun isInternetAvailable(context : Context) : Boolean{
        val info = (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
        return if (info == null){
            false
        }else{
            if (info.isConnected){
                true
            }else{
                true
            }
        }
    }
}