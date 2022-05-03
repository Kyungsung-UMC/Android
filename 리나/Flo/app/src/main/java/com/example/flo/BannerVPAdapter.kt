package com.example.flo

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class BannerVPAdapter(fragment:Fragment) : FragmentStateAdapter(fragment) {

    private val fragmentlist : ArrayList<Fragment> = ArrayList() // 초기화 꼭 해주기

    override fun getItemCount(): Int  = fragmentlist.size// 배열의 크기

    override fun createFragment(position: Int): Fragment = fragmentlist[position] // override -> fragment 생성

    fun addFragment(fragment: Fragment) {
        fragmentlist.add(fragment)
        notifyItemInserted(fragmentlist.size-1) // 1234 -> 0123
    }
}