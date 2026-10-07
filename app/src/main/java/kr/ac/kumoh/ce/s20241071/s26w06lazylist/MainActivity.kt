package kr.ac.kumoh.ce.s20241071.s26w06lazylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kr.ac.kumoh.ce.s20241071.s26w06lazylist.model.Song
import kr.ac.kumoh.ce.s20241071.s26w06lazylist.ui.theme.S26W06LazyListTheme
import kr.ac.kumoh.ce.s20241071.s26w06lazylist.viewmodel.SongViewModel

class MainActivity : ComponentActivity() {
    private val songViewModel: SongViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            S26W06LazyListTheme {
                MainScreen(songViewModel)
            }
        }
    }
}

@Composable
fun MainScreen(
    viewModel: SongViewModel
) {
    val listSong by viewModel.songs.collectAsStateWithLifecycle()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        MyList(
            modifier = Modifier.padding(innerPadding),
            songs = listSong,
        )
    }
}

@Composable
fun MyList(
    modifier: Modifier = Modifier,
    songs: List<Song>,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(songs) {
            SongItem(it)
        }
    }
}

@Composable
fun SongItem(song: Song) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xffffffcc))
            .padding(16.dp)
    ) {
        TextTitle("노래 ${song.title}")
        TextSinger("이 노래를 부른 가수는 ${song.singer} 입니다")
    }
}

@Composable
fun TextTitle(title: String) {
    Text(title, fontSize = 30.sp)
}

@Composable
fun TextSinger(singer: String) {
    Text(singer, fontSize = 20.sp)
}

