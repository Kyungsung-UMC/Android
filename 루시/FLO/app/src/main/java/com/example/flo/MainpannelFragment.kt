package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flo.databinding.FragmentHomepannelBinding

class MainpannelFragment(var imgRes : Int) : Fragment() {

    lateinit var binding : FragmentHomepannelBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomepannelBinding.inflate(inflater,container,false)
        binding.homePannelBackgroundIv.setImageResource(imgRes)

        return binding.root
    }

}