package com.example.onlinemarket.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.onlinemarket.Adapter.SotuvchiXaridorAdapter
import com.example.onlinemarket.Models.Sotuvchi
import com.example.onlinemarket.Models.Xaridor
import com.example.onlinemarket.databinding.FragmentSotuvchiBinding
import com.example.onlinemarket.db.MyDbHelper
import java.util.ArrayList

class XaridorFragment:Fragment() {
    private val binding by lazy { FragmentSotuvchiBinding.inflate(layoutInflater) }
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var sotuvchiXaridorAdapter: SotuvchiXaridorAdapter<Xaridor>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.apply {
            edtAdres.visibility = View.VISIBLE
            btnSaqlash.setOnClickListener {
                val sotuvchi = Xaridor(
                    0,
                    edtName.text.toString().trim(),
                    edtRaqam.text.toString().trim(),
                    edtAdres.text.toString().trim()
                )
                myDbHelper.addXaridor(sotuvchi)
                onResume()
                edtName.text.clear()
                edtRaqam.text.clear()
                edtAdres.text.clear()
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        myDbHelper= MyDbHelper(binding.root.context)
        sotuvchiXaridorAdapter = SotuvchiXaridorAdapter(myDbHelper.getAllXaridor() as ArrayList<Xaridor>)
        binding.rv.adapter = sotuvchiXaridorAdapter
    }
}