package com.example.flo

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.flo.databinding.FragmentAlbumBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AlbumFragment : Fragment(){

    lateinit var binding : FragmentAlbumBinding

    private val information = arrayListOf("수록곡", "상세정보", "영상")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumBinding.inflate(inflater,container,false)


        binding.albumBtnArrowBlackIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.main_frm,HomeFragment()).commitAllowingStateLoss()
        }


        // 어댑터 연결
        val albumAdapter = AlbumVPAdapter(this)
        binding.albumContentVp.adapter = albumAdapter
        // TabLayoutMediator = viewpage2와 연결하는 중재자 , 탭이 선택될때 viewpage2에 위치를 선택한 탭과 동기화 하고 사용자가 viewpage2의 스크롤 위치를 동기화
        TabLayoutMediator(binding.albumContentTb, binding.albumContentVp) { tab, posiaion ->
            tab.text = information[posiaion]
            //연결

        }.attach()
        return binding.root
    }




}











