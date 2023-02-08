package com.example.learn_jetpack_compose.ApiService

import com.example.learn_jetpack_compose.BuildConfig
import com.example.learn_jetpack_compose.Service.ProductService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class ApiManager {

    protected lateinit var productService: ProductService

    init {
        initService(initRetrofit())
    }
    private fun initRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun initService(retrofit: Retrofit){
       productService = retrofit.create(ProductService::class.java)
    }
}