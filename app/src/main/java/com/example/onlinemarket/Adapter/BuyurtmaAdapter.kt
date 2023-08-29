package com.example.onlinemarket.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinemarket.Models.Buyurtma
import com.example.onlinemarket.databinding.ItemRvBinding


class BuyurtmaAdapter(var list:ArrayList<Buyurtma>): RecyclerView.Adapter<BuyurtmaAdapter.Vh>() {

    inner class Vh(val itemRvBinding: ItemRvBinding): RecyclerView.ViewHolder(itemRvBinding.root){
        fun onBind(buyurtma: Buyurtma){
            itemRvBinding.txtName.text = buyurtma.maxsulot
            itemRvBinding.txtPhone.text = buyurtma.data
            itemRvBinding.txtSotuvci.visibility = View.VISIBLE
            itemRvBinding.txtSotuvci.text = buyurtma.sotuvchi.name
            itemRvBinding.txtXaridor.text = buyurtma.xaridor.name
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