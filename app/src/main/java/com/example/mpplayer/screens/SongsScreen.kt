package com.example.mpplayer.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.example.mpplayer.view.models.PlaylistViewModelModel
import com.example.mpplayer.view.models.SongViewModel

@Composable
fun SongsScreen(
    navController: NavController,
    songViewModel: SongViewModel,
    playlistViewModelModel: PlaylistViewModelModel
) {
    val songsList by songViewModel.songsList.observeAsState(emptyList())

    LazyColumn {
        items(songsList.size) { index ->
            val song = songsList[index]
            Box() {
//                Image(painter = painterResource(id = R.drawable.d))
                Column() {
                    Text(song.title)
                    Text(song.artist)
                    // TODO: Add Small image next to title
                    // TODO: Place Stars icons based on song.rating
//                    Column {
//                        for
//                    }
                }
            }
        }
    }
}