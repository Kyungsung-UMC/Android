package com.example.flo

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class AlbumVPAdapter(fragment : Fragment) : FragmentStateAdapter(fragment) {


    // class에서 연결된 viewpage에 데이터를 전달할 때 몇개의 데이터를 전달할 것인가
    override fun getItemCount(): Int = 3


    //when = 조건에 따라 다른 작업을 해줄 수 있도록 하는 것
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> SongFragment()
            1 -> DetailFragment()
            else -> VideoFragment()
        }
    }

}