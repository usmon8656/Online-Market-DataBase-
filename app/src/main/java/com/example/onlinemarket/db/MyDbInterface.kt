package com.example.onlinemarket.db

import com.example.onlinemarket.Models.Buyurtma
import com.example.onlinemarket.Models.Sotuvchi
import com.example.onlinemarket.Models.Xaridor

interface MyDbInterface {
    fun addSotuvchi(sotuvchi: Sotuvchi)
    fun getAllSotuvchi():List<Sotuvchi>

    fun addXaridor(xaridor: Xaridor)
    fun getAllXaridor():List<Xaridor>


    fun addBuyurtma(buyurtma: Buyurtma)
    fun getAllBuyurtma():List<Buyurtma>

    fun getXaridorById(id:Int):Xaridor
    fun getSotuvchiById(id:Int):Sotuvchi
}