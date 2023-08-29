package com.example.onlinemarket.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.onlinemarket.Adapter.BuyurtmaAdapter
import com.example.onlinemarket.Adapter.SotuvchiXaridorAdapter
import com.example.onlinemarket.Models.Buyurtma
import com.example.onlinemarket.Models.Sotuvchi
import com.example.onlinemarket.Models.Xaridor
import com.example.onlinemarket.databinding.FragmentSotuvchiBinding
import com.example.onlinemarket.db.MyDbHelper

class BuyurtmaFragment:Fragment() {
    private val binding by lazy { FragmentSotuvchiBinding.inflate(layoutInflater) }
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var buyurtmaAdapter: BuyurtmaAdapter
    private lateinit var sotuvchiList:ArrayList<Sotuvchi>
    private lateinit var xaridorList:ArrayList<Xaridor>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        myDbHelper= MyDbHelper(binding.root.context)
        binding.edtName.hint = "Mahsulot"
        binding.edtRaqam.hint = "Sana"
        binding.spSotuvchi.visibility = View.VISIBLE
        binding.spXaridor.visibility = View.VISIBLE


        sotuvchiList = myDbHelper.getAllSotuvchi() as ArrayList<Sotuvchi>
        xaridorList = myDbHelper.getAllXaridor() as ArrayList<Xaridor>

        val sotuvchiNameList = ArrayList<String>()
        val xaridorNameList = ArrayList<String>()

        sotuvchiList.forEach {
            sotuvchiNameList.add(it.name)
        }
        xaridorList.forEach {
            xaridorNameList.add(it.name)
        }

        binding.spSotuvchi.adapter = ArrayAdapter<String>(binding.root.context, android.R.layout.simple_list_item_1, sotuvchiNameList )
        binding.spXaridor.adapter = ArrayAdapter<String>(binding.root.context, android.R.layout.simple_list_item_1, xaridorNameList )

        binding.btnSaqlash.setOnClickListener {
            val buyurtma = Buyurtma(
                0,
                binding.edtName.text.toString().trim(),
                binding.edtRaqam.text.toString().trim(),
                sotuvchiList[binding.spSotuvchi.selectedItemPosition],
                xaridorList[binding.spXaridor.selectedItemPosition]
            )
            myDbHelper.addBuyurtma(buyurtma)
            onResume()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        buyurtmaAdapter = BuyurtmaAdapter(myDbHelper.getAllBuyurtma() as ArrayList<Buyurtma>)
        binding.rv.adapter = buyurtmaAdapter
    }
}