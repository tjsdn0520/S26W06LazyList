package kr.ac.kumoh.ce.s20241071.s26w06lazylist.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kr.ac.kumoh.ce.s20241071.s26w06lazylist.model.Song

class SongViewModel : ViewModel() {
    private val _songs = MutableStateFlow<List<Song>>(emptyList())
    val songs = _songs.asStateFlow()

    init {
        var id = 1

        repeat(30) { index ->
            add(Song(id++, "Neon Horizon $index", "Pixel Wave"))
            add(Song(id++, "Midnight Coffee $index", "The Afterhours"))
            add(Song(id++, "Gravity Reset $index", "Lunarcat"))
        }
    }

    fun add(song: Song) {
        // 새로운 리스트를 만들고 song 추가
        // Shallow copy
        _songs.update { it + song }
    }
}