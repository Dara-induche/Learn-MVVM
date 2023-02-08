package com.example.learn_jetpack_compose

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.MutableLiveData
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
    private val productModel =  MutableStateFlow(ProductModel())
    private val productList  =  MutableStateFlow(arrayListOf<ProductModel.ProductInformation>())
    private var isSuccess    =  MutableStateFlow(false)

    var getProductModel  = productModel.asStateFlow()
    var getIsSuccess     = isSuccess.asStateFlow()
    var getProductList   = productList.asStateFlow()

    fun callAllProduct(){
        viewModelScope.launch {
            try {
                productModel.value = ApiProductImp().getAllProduct()
                if (productModel.value.products.isNotEmpty()){
                    isSuccess.value = true
                    productList.value.addAll(productModel.value.products)
                }
            }catch (e:Error){
                isSuccess.value = false
            }
        }
    }



}