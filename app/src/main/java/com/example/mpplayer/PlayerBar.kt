package com.example.mpplayer

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.mpplayer.view.models.PlayerViewModel
import com.example.mpplayer.view.models.SongState

@Composable
fun PlayerBar(playerViewModel: PlayerViewModel, navController: NavController, modifier: Modifier) {
    val isPlaying by playerViewModel.isPlaying.observeAsState(SongState.STOPPED)
    val song by playerViewModel.mSong.observeAsState(null)

    Box(
        modifier = modifier
            .border(1.dp, Color.Black)
            .background(Color.Black)
            .zIndex(1f)
            .clickable {
                playerViewModel.changeSongState(if (isPlaying == SongState.PAUSED) SongState.IN_FOCUS_PAUSED else SongState.IN_FOCUS_PLAYING)
                navController.navigate("songScreen")
            },
        contentAlignment = Alignment.BottomStart
    ) {
        when (isPlaying) {
            SongState.PLAYING -> {
                Row {
                    song?.let { BitmapImage(it.imagePath) }
                    Column(Modifier.weight(1f)) {
                        Text("${song?.title}")
                        Text("${song?.artist}")
                    }
                    Button(onClick = {
                        playerViewModel.togglePlayPause()
                    }) {
                        Icon(
                            Icons.Rounded.Info,
                            contentDescription = "Pause"
                        )
                    }
                }
            }

            SongState.PAUSED -> {
                return
//                Row {
//                    song?.let { BitmapImage(it.imagePath) }
//                    Column(Modifier.weight(1f)) {
//                        Text("${song?.title}")
//                        Text("${song?.artist}")
//                    }
//                    Button(onClick = {
//                        playerViewModel.togglePlayPause()
//                    }) {
//                        Icon(
//                            Icons.Rounded.PlayArrow,
//                            contentDescription = "Play"
//                        )
//                    }
//                }
            }

            SongState.STOPPED -> {
                return
            }

            SongState.IN_FOCUS_PLAYING -> {
                return
            }

            SongState.IN_FOCUS_PAUSED -> {
                return
            }
        }

    }
}