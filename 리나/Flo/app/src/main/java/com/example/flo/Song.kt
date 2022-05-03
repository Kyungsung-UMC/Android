package com.example.flo

import androidx.room.Entity
import androidx.room.PrimaryKey

// 제목, 가수, 사진, 재생시간, 현재 재생시간, isplaying(재생되고 있는지)

@Entity (tableName = "SongTable")
data class Song(
    val title : String = "", // 초기화후 다음 변수 적어주면 됨
    val singer : String = "",
    var second : Int = 0, //노래가 얼마나 재생 되었는지
    var playTime : Int = 0, //총 재생 시간은 얼마인지
    var isPlaying : Boolean = false, // 노래가 재생중인지
    var music: String = "", //실제로 어떤 음악이 재생되어야 하는지 알려줌
    var coverImg: Int? = null,
    var isLike: Boolean = false
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
