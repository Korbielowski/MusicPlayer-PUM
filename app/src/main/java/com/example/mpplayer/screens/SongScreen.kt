package com.example.mpplayer.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mpplayer.BitmapImage
import com.example.mpplayer.BottomBar
import com.example.mpplayer.view.models.PlayerViewModel
import com.example.mpplayer.view.models.PlaylistSongsViewModel
import com.example.mpplayer.view.models.SongState
import com.example.mpplayer.view.models.SongViewModel

@Composable
fun SongScreen(
    navController: NavHostController,
    playerViewModel: PlayerViewModel,
    songViewModel: SongViewModel,
    playlistSongsViewModel: PlaylistSongsViewModel
) {
    DisposableEffect(Unit) {
        onDispose {
            playerViewModel.changeSongState(if (playerViewModel.isPlaying.value == SongState.IN_FOCUS_PLAYING) SongState.PLAYING else SongState.PAUSED)
        }
    }
    Scaffold(bottomBar = { BottomBar(navController = navController) }) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                playerViewModel.mSong.value?.let { BitmapImage(it.imagePath) }
                Text("Title: ${playerViewModel.mSong.value?.title}")
                Text("Album: ${playerViewModel.mSong.value?.album}")
                Text("Artist: ${playerViewModel.mSong.value?.artist}")
                Text("Genre: ${playerViewModel.mSong.value?.genre}")
                // TODO: Place Stars icons based on song.rating
                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(modifier = Modifier
                        .weight(1f), onClick = {
                        navController.navigate("addSongToPlaylist/${playerViewModel.mSong.value?.songId}")
                    }) {
                        Icon(
                            Icons.Rounded.Add,
                            contentDescription = "Add song to a playlist"
                        )
                    }
                    Button(modifier = Modifier
                        .weight(1f), onClick = {
                        navController.navigate("editSong/${playerViewModel.mSong.value?.songId}")
                    }) {
                        Icon(
                            Icons.Rounded.Edit,
                            contentDescription = "Edit song"
                        )
                    }
                    Button(modifier = Modifier
                        .weight(1f), onClick = {
                        playerViewModel.mSong.value?.songId?.let { songViewModel.deleteSong(it) }
                        playerViewModel.mSong.value?.songId?.let {
                            playlistSongsViewModel.deleteSong(
                                it
                            )
                        }
                    }) {
                        Icon(
                            Icons.Rounded.Delete,
                            contentDescription = "Delete song"
                        )
                    }
                }
            }
        }
    }
}