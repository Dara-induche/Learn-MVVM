package com.example.learn_jetpack_compose.Model

class ProductModel {
    var products = arrayListOf<ProductInformation>()
    var total :Int?  =null
    var skip  :Int?  =null
    var limit :Int?  = null


    class ProductInformation:java.io.Serializable{
        var id:Int?=null
        var title:String?=null
        var description:String?=null
        var price:Float?=null
        var discountPercentage:Float?=null
        var rating:Float?=null
        var stock:Int?=null
        var brand:String?=null
        var category:String?=null
        var thumbnail:String?=null
        var images = arrayListOf<String>()
    }
}