package com.example.onlinemarket.Models

data class Buyurtma(
    val id:Int=0,
    val maxsulot:String,
    val data:String,
    val sotuvchi: Sotuvchi,
    val xaridor: Xaridor
)
