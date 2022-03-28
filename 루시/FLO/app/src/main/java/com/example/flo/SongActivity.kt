package com.example.flo

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.databinding.ActivitySongBinding

class SongActivity:  AppCompatActivity(){

    lateinit var binding: ActivitySongBinding
    var isClick : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.songRepeatIv.setOnClickListener {
            if(isClick == true){
            setchanelist(isClick)
            isClick = false }
            else{
                setchanelist(isClick)
                isClick = true }
        }

        binding.songMiniplayerIv.setOnClickListener {
            setPlaystatus(false)
        }

        binding.songPauseIv.setOnClickListener {
            setPlaystatus(true)
        }

        binding.songNuguBtnDownIv.setOnClickListener {
            finish()
        }
        if(intent.hasExtra("title") && intent.hasExtra("singer")){
            binding.songMusicTitleTv.text = intent.getStringExtra("title")
            binding.songMusicSingerTv.text = intent.getStringExtra("singer")
        }
    }

    fun setPlaystatus(isPlaying : Boolean){
        if(isPlaying){
            binding.songMiniplayerIv.visibility = View.VISIBLE
            binding.songPauseIv.visibility = View.GONE
        }
        else{
            binding.songMiniplayerIv.visibility = View.GONE
            binding.songPauseIv.visibility = View.VISIBLE
        }
    }

    fun setchanelist(isClick: Boolean){
        if(isClick){
            binding.songRepeatIv.setImageResource(R.drawable.nugu_btn_repeat_inactive_on)

        }
        else{
            binding.songRepeatIv.setImageResource(R.drawable.nugu_btn_repeat_inactive)
        }
    }


}
