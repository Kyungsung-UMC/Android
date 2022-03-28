package com.example.flo

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class BannerVPAdapter(fragment : Fragment) : FragmentStateAdapter(fragment) {

    //여러개의 fragment를 담아두는 공간(list)
    private val fragmentList : ArrayList<Fragment> = ArrayList()

    // class에서 연결된 viewpage에 데이터를 전달할 때 몇개의 데이터를 전달할 것인가
    override fun getItemCount(): Int {
        //fragmentList안의 메소드 값만큼 return
        return fragmentList.size
    }
//     이렇게도 사용 가능
//    override fun getItemCount(): Int = return fragmentList.size


    override fun createFragment(position: Int): Fragment  = fragmentList[position] // 0,1,2,3


    // 함수가 처음 실행될떈 list엔 아무것도 없으니 homefragment에서 추가해주기 위해 함수 사용
    fun addFragment(fragment : Fragment){
        fragmentList.add(fragment)
        // list안에 새로운 값이 추가되었을때 viewpage에 알려주는 메소드
        notifyItemInserted(fragmentList.size -1)
    }


}