package com.example.onlinemarket.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.onlinemarket.Adapter.SotuvchiXaridorAdapter
import com.example.onlinemarket.Models.Sotuvchi
import com.example.onlinemarket.databinding.FragmentSotuvchiBinding
import com.example.onlinemarket.db.MyDbHelper
import java.util.ArrayList

class SotuvchiFragment:Fragment() {
    private val binding by lazy { FragmentSotuvchiBinding.inflate(layoutInflater) }
    private lateinit var sotuvchiXaridorAdapter:SotuvchiXaridorAdapter<Sotuvchi>
    private lateinit var myDbHelper: MyDbHelper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.btnSaqlash.setOnClickListener {
            val sotuvchi = Sotuvchi(
                0,
                binding.edtName.text.toString().trim(),
                binding.edtRaqam.text.toString().trim()
            )
            myDbHelper.addSotuvchi(sotuvchi)
            onResume()
            binding.edtName.text.clear()
            binding.edtRaqam.text.clear()
        }


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        myDbHelper= MyDbHelper(binding.root.context)
        sotuvchiXaridorAdapter = SotuvchiXaridorAdapter(myDbHelper.getAllSotuvchi() as ArrayList<Sotuvchi>)
        binding.rv.adapter = sotuvchiXaridorAdapter
    }
}