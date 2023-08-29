package com.example.onlinemarket.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.onlinemarket.Models.Sotuvchi
import com.example.onlinemarket.Models.Xaridor
import com.example.onlinemarket.databinding.ItemRvBinding
import java.lang.Exception

class SotuvchiXaridorAdapter<T>(var list:ArrayList<T> = ArrayList()):RecyclerView.Adapter<SotuvchiXaridorAdapter<T>.Vh>() {

    inner class Vh(val itemRvBinding: ItemRvBinding):ViewHolder(itemRvBinding.root){
        fun onBind(myObject:T){
            try {
                val sotuvchi:Sotuvchi = myObject as Sotuvchi
                itemRvBinding.txtName.text = sotuvchi.name
                itemRvBinding.txtPhone.text = sotuvchi.phone
            }catch (e:Exception){
                val xaridor:Xaridor = myObject as Xaridor
                itemRvBinding.txtName.text = xaridor.name
                itemRvBinding.txtPhone.text = xaridor.phone
                itemRvBinding.txtAdress.visibility = View.VISIBLE
                itemRvBinding.txtAdress.text = xaridor.adres
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
       return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }
}