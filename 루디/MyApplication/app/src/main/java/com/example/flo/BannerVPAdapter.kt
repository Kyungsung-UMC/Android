package com.example.flo

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class BannerVPAdapter(fragment:Fragment) : FragmentStateAdapter(fragment) {
    private val fragmentlist : ArrayList<Fragment> = ArrayList()

    override fun getItemCount(): Int = fragmentlist.size //배열의 크기 1,2,3,4 -> 0,1,2,3

    override fun createFragment(position: Int): Fragment = fragmentlist[position]

    fun addFragment(fragment: Fragment){
        fragmentlist.add(fragment)
        notifyItemInserted(fragmentlist.size-1)
    }
}
//fragmentlist.size-1 index를 0 1 2 3을 사용하기 위해
//array 배열을 두 개 쓰겠다
//binding.homeBannerVp.adapter = bannerAdapter
//binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL