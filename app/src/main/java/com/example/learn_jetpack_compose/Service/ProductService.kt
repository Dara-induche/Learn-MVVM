package com.example.learn_jetpack_compose.Service

import com.example.learn_jetpack_compose.ApiService.ApiEndPoint
import com.example.learn_jetpack_compose.Model.ProductModel
import retrofit2.http.GET

interface ProductService {
   @GET(ApiEndPoint.PRODUCT)
   suspend fun getAllProduct():ProductModel
}