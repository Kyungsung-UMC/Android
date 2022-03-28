package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.flo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.homeAlbumImgIv1.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.main_frm,AlbumFragment()).commitAllowingStateLoss()
        }

        val bannerAdapter = BannerVPAdapter(this)
        //list에 이미지 추가
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))
        // adapter와 binding 연결
        binding.homeBannerVp.adapter = bannerAdapter
        // viewpage가 좌우로 스크롤 될 수 있게 하는 코드
        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL


        val mainpanneladapter = MainpannelVPAdapter(this)
        mainpanneladapter.addFragment(MainpannelFragment(R.drawable.img_first_album_default))
        mainpanneladapter.addFragment(MainpannelFragment(R.drawable.img_second_album_default))
        binding.homePannelContentVp.adapter = mainpanneladapter
        binding.homePannelContentVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        return binding.root
    }
}