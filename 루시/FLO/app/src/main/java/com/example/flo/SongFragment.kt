package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.flo.databinding.FragmentDetailBinding
import com.example.flo.databinding.FragmentSongBinding

class SongFragment : Fragment() {

    lateinit var binding : FragmentSongBinding
    var isClick : Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSongBinding.inflate(inflater,container,false)
        binding.songLalacLayout.setOnClickListener{
            Toast.makeText(activity,"LILAC", Toast.LENGTH_SHORT).show()
            // 어디서띄울것인지 , 어떤걸 띄울것인지 , 얼마나 띄울것인지
        }

        binding.songMixoffTg.setOnClickListener {
            if(isClick == true) {
                albumBtnToggleStatus(isClick)
                isClick = false
            }else{
                albumBtnToggleStatus(isClick)
                isClick = true
            }

        }

        return binding.root
    }

    fun albumBtnToggleStatus(isClick : Boolean){
        if(isClick){
            binding.songMixoffTg.setImageResource(R.drawable.btn_toggle_on)
        }
        else{
            binding.songMixoffTg.setImageResource(R.drawable.btn_toggle_off)
        }
    }
}