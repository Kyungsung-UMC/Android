package com.example.flo

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.databinding.ActivitySongBinding
import com.google.gson.Gson
import java.util.*

class SongActivity : AppCompatActivity() { // :의 의미는 상속 받는 다는 의미이며 ()도 꼭 넣어야함 지금은 AppCompatActivity를 상속 받는 다는 뜻임

    lateinit var binding : ActivitySongBinding // lateinit -> 전방선언, 선언은 지금 하고 초기화는 나중에함
    // val 은 나중에 변수 변경 안됨  var은 나중에 변수 변경 가능
    // 지금은 activitysong을 binding 한다는 뜻
    lateinit var timer: Timer

    //음악을 재생 시켜 주는 코드
    private var mediaPlayer: MediaPlayer? = null //?는 null값이 들어오겠다는 의미
                                                 // null로 해준 이유는 activity가 소멸될때 mediaplayer를 해제시켜줘야하기 때문에

    private var gson: Gson = Gson()

    val songs = arrayListOf<Song>()
    lateinit var songDB: SongDatabase
    var nowPos = 0

    override fun onCreate(savedInstanceState: Bundle?) {  //activity가 생성될때 가장 먼저 실행 되는 것 / fun -> 함수 / override -> ?
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)  //binding 초기화 시켜줌 / inflate -> 메모리를 객체화 시킴
        setContentView(binding.root) // setContentView -> xml에 있는 것을 가져와서 마음대로 사용 할꺼야 라는 것을 의미 / ()안에는 사용할 id 이름적어줌

        initPlayList()
        initSong() //데이터를 받아줌
        initClickListener()
    }


    private fun initSong(){ //전역변수 Song
//        if (intent.hasExtra("title") && intent.hasExtra("singer")) {
//            song = Song(
//                intent.getStringExtra("title")!!,
//                intent.getStringExtra("singer")!!,
//                intent.getIntExtra("second",0),
//                intent.getIntExtra("playTime",0),
//                intent.getBooleanExtra("isPlaying",false),
//                intent.getStringExtra("music")!!
//            ) //mainactivity랑 주고받음
//        }
        val spf = getSharedPreferences("song", MODE_PRIVATE)
        val songId = spf.getInt("songId", 0)

        nowPos = getPlayingSongPosition(songId)

        Log.d("now Song ID", songs[nowPos].id.toString())

        startTimer()
        setPlayer(songs[nowPos])
    }

    private fun setLike(isLike: Boolean){
        songs[nowPos].isLike = !isLike
        songDB.songDao().updateIsLikeById(!isLike,songs[nowPos].id)

        if (!isLike) {
            binding.songLikeIv.setImageResource(R.drawable.ic_my_like_on)
        } else {
            binding.songLikeIv.setImageResource(R.drawable.ic_my_like_off)
        }

    }

    private fun moveSong(direct: Int) {
        if (nowPos + direct < 0) {
            Toast.makeText(this,"first song", Toast.LENGTH_SHORT).show()
            return
        }
        if (nowPos + direct >= songs.size) {
            Toast.makeText(this,"last song", Toast.LENGTH_SHORT).show()
            return
        }
        nowPos += direct

        timer.interrupt()
        startTimer()

        mediaPlayer?.release() //미디어플레이어가 갖고 있던 리소스 해제
        mediaPlayer = null // 미디어 플레이어 해제

        setPlayer(songs[nowPos])

    }

    private fun getPlayingSongPosition(songId: Int): Int{
        for (i in 0 until songs.size) {
            if (songs[i].id == songId) {
                return i
            }
        }
        return 0
    }

    private fun setPlayer (song: Song) { //초기화된 song의 정보를 view 렌더링해줌
        binding.songMusicTitleTv.text = song.title
        binding.songSingerNameTitleTv.text = song.singer
        binding.songStrartTimeTv.text = String.format("%02d:%02d", song.second / 60, song.second % 60)
        binding.songEndTimeTv.text = String.format("%02d:%02d", song.playTime / 60, song.playTime % 60)
        binding.homePannelBackgroundIv.setImageResource(song.coverImg!!)
        binding.songProgressbarSb.progress = (song.second * 1000 / song.playTime)

        val music = resources.getIdentifier(song.music,"raw",this.packageName)  //resouces 파일에서 해당 string 값을 찾아서 resurce를 받아줄 무언가가 필요함
        mediaPlayer = MediaPlayer.create(this,music) //resource로 받았으니 mediaplayer에 물려줘야함 / 이 음악을 재생할꺼야

        if (song.isLike) {
            binding.songLikeIv.setImageResource(R.drawable.ic_my_like_on)
        } else {
            binding.songLikeIv.setImageResource(R.drawable.ic_my_like_off)
        }

        setPlayerStatus(song.isPlaying)

    }

    private fun setPlayerStatus (isPlaying : Boolean){

        songs[nowPos].isPlaying = isPlaying //초기화 해줘야 멈춰지고 재생이 됨
        timer.isPlaying = isPlaying


        if(isPlaying){ //// 재생버튼은 보이게 정시 버튼은 안보이게 해주는 함수
            binding.songMiniplayerIv.visibility = View.VISIBLE
            binding.songPauseIv.visibility = View.GONE
            mediaPlayer?.start() // 재생시켜줌
        } else {
            binding.songMiniplayerIv.visibility = View.GONE
            binding.songPauseIv.visibility = View.VISIBLE
            if (mediaPlayer?.isPlaying == true) { //정지시킴
                mediaPlayer?.pause()
            }
        }
    }

    private fun startTimer() { //시작과 동시에 timer가 할수 있도록 함
        timer = Timer(songs[nowPos].playTime,songs[nowPos].isPlaying)
        timer.start()
    }

    inner class Timer(private val playTime:Int, var isPlaying: Boolean = true):Thread() { //Timer 클래스를 걸어주고 Thread 시켜줌
        //inner class는 내부 클래스 라는 말인데 자바의 경우 클래스내에 클래스를 사용하면 자동으로 내부 클래스 라고 판단
        //코틀린은 inner 없이 클래스를 만들면 외부에 클래스에 진입할수 없다
        // 시간에 지남에 따라 Timer textview에 값을 바꿔줘야 하기 때문에 binding 함수로 사용해야 함으로 inner 클래스를 사용함
        private var second : Int = 0 //노래의 진행단위는 second, mills
        private var mills : Float = 0f

        override fun run() {
            super.run()
            try { //오류가 나도 프로그램이 종류가 되는게 아님
                while (true) { //timer는 계속 진행되어야 되기 때문에 / 하지만 계속해서 실행되고 있음

                    if (second >= playTime) { //노래 시간이 끝나면 반복문은 종료료
                        break
                    }
                    if (isPlaying) {
                        sleep(50)
                        mills += 50 //sleep을 50으로 줬기 때문에

                        runOnUiThread { //handler를 사용해도 무관 -> 자기가 원하는 그림으로 다룰 수 있게 함
                            binding.songProgressbarSb.progress = ((mills / playTime) * 100).toInt() //progress 값이 나옴
                        }

                        if (mills % 1000 == 0f) { //진행하는 타이머도 나타내줌 1초가 지나면 1을 더해줌
                            runOnUiThread { //view 렌더링 작업
                                binding.songStrartTimeTv.text = String.format("%02d:%02d", second / 60, second % 60)
                            }
                            second++
                        }
                    }
                }
            }catch (e : InterruptedException) {
                Log.d("Song", "쓰레드가 죽었습니다. ${e.message}")
            }
        }
    }

    //사용자가 포커스를 잃었을때 음악이 중지
    override fun onPause() {
        super.onPause()

        songs[nowPos].second = ((binding.songProgressbarSb.progress * songs[nowPos].playTime)/100)/1000 //song이 몇초까지 재생되었는지 알려주는 코드
                                                                                      // miils로 계산되고 있는데 초단위로 바꿔주기 위해서
        songs[nowPos].isPlaying = false
        setPlayerStatus(false) //음악중지
        val sharedPreferences = getSharedPreferences("song", MODE_PRIVATE) //데이터 저장 mode -> private하게 여기서만 사용
            //내부 저장소에 데이터를 저장 할 수 있게 해줌 앱이 종료되었다가 실행되도 저장된 데이터를 실행핼수 있게 해줌
        val editor = sharedPreferences.edit() //에디터
        editor.putInt("songId", songs[nowPos].id)

        editor.apply() // 호출을 해줘야 저장됨
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.interrupt()

    }

    private fun initPlayList() {
    songDB = SongDatabase.getInstance(this)!!
    songs.addAll(songDB.songDao().getSongs())
    }

    private fun initClickListener(){
        binding.songDownIb.setOnClickListener { //  // songDownIb를 binding 시켰으며 클릭하면 어떠한 동작이 나타나도록 하라는 의미
            startActivity(Intent(this,MainActivity::class.java))
        }

        binding.songMiniplayerIv.setOnClickListener {
            setPlayerStatus(false)
        }

        binding.songPauseIv.setOnClickListener {
            setPlayerStatus(true)
        }

        binding.songNextIv.setOnClickListener {
            moveSong(+1)

        }

        binding.songPreviousIv.setOnClickListener{
            moveSong(-1)

        }

        binding.songLikeIv.setOnClickListener {
            setLike(songs[nowPos].isLike)

        }
    }


}

// Thread를 사용해서 구현하는 이유?
// Thread를 사용하지 않으면 어떠한 작동을 할때 다른 작동을 못하게됨