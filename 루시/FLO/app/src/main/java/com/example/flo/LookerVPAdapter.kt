package com.example.flo

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class LookerVPAdapter(fragment : Fragment) : FragmentStateAdapter(fragment)   {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> SongSavedFragment()
            else -> MusicfileFragment()
        }
    }

}