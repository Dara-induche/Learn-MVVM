package com.example.learn_jetpack_compose.CheckInternet

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log

class CheckConnectionActivity {

    companion object{
        fun checkConnection(context: Context):Boolean{

            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val network = connectivityManager.activeNetwork ?: return false

            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // case user use wifi
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ->{
                    Log.d("f123456","Wifi")
                    true
                }
                // case user use internet sim
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.d("f123456","Sim")
                    true
                }
                //case no internet
                else -> {
                    false
                }
            }
        }
    }
}