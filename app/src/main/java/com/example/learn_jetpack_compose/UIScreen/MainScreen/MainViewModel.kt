package com.example.learn_jetpack_compose.UIScreen.MainScreen

import android.annotation.SuppressLint
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learn_jetpack_compose.ApiService.ApiProductImp
import com.example.learn_jetpack_compose.Model.ProductModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


@SuppressLint("MutableCollectionMutableState")
class MainViewModel:ViewModel() {
    private var productModel =  MutableStateFlow(ProductModel())
    private var productList  =  MutableStateFlow(arrayListOf<ProductModel.ProductInformation>())

    var getProductList  = productList.asStateFlow()
    var getProductModel = productModel.asStateFlow()

    init {
        callAllProduct()
    }
  private fun callAllProduct(){
        productModel.value.isLoading = true
        viewModelScope.launch {
            try {
                delay(1000)
                productModel.value = ApiProductImp().getAllProduct()
                if (productModel.value.products.isNotEmpty()){
                    productModel.value.isLoading = false
                    productList.value.addAll(productModel.value.products)
                }

            }
            catch (_:Error)
            {

            }
        }
    }

    fun callNextPage(){
        productModel.value.isLoading = false
        productModel.value.isLoadingNext = true
        viewModelScope.launch {
            try {
                delay(1000)
                productModel.value = ApiProductImp().getAllProduct()
                if (productModel.value.products.isNotEmpty()){
                    productModel.value.isLoadingNext = false
                    productModel.value.isLoading = false
                    productList.value.addAll(productModel.value.products)
                }
            }
            catch (_:Error)
            {

            }
        }
    }
}