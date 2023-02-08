package com.example.learn_jetpack_compose.ApiService

import com.example.learn_jetpack_compose.Model.ProductModel

class ApiProductImp : ApiManager() {
  suspend fun getAllProduct():ProductModel = productService.getAllProduct()
}