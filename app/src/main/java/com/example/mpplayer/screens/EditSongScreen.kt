package com.example.mpplayer.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mpplayer.BottomBar
import com.example.mpplayer.view.models.PlayerViewModel
import com.example.mpplayer.view.models.PlaylistSongsViewModel
import com.example.mpplayer.view.models.PlaylistViewModel
import com.example.mpplayer.view.models.SongViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditSongScreen(
    navController: NavController,
    songViewModel: SongViewModel,
    playlistViewModel: PlaylistViewModel,
    playlistSongsViewModel: PlaylistSongsViewModel,
    playerViewModel: PlayerViewModel,
    songId: Long
) {
    songViewModel.getSong(songId)
    val song by songViewModel.selectedSong.observeAsState()
    var title by remember { mutableStateOf(song?.title) }
    var description by remember { mutableStateOf(song?.description) }
    var artist by remember { mutableStateOf(song?.artist) }
    var album by remember { mutableStateOf(song?.album) }
    var genre by remember { mutableStateOf(song?.genre) }
    var rating by remember { mutableStateOf(song?.rating) }
    Scaffold(bottomBar = { BottomBar(navController = navController) }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            TextField(
                value = title ?: "",
                onValueChange = { title = it },
                label = { Text("Enter song's name") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = description ?: "",
                onValueChange = { description = it },
                label = { Text("Enter song's description") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = artist ?: "",
                onValueChange = { artist = it },
                label = { Text("Enter song's artist") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = album ?: "",
                onValueChange = { album = it },
                label = { Text("Enter song's album") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = genre ?: "",
                onValueChange = { genre = it },
                label = { Text("Enter song's genre") },
                modifier = Modifier.fillMaxWidth()
            )
            Slider(
                value = rating?.toFloat() ?: 0f,
                onValueChange = { rating = it.toInt() },
                valueRange = 1f..5f,
                steps = 3
            )
            Button(onClick = {
                songViewModel.editSong(
                    songId = songId,
                    title = title ?: "",
                    description = description ?: "",
                    artist = artist ?: "",
                    album = album ?: "",
                    genre = genre ?: "",
                    rating = rating ?: 0,
                    playerViewModel = playerViewModel
                )
                navController.navigate("mainScreen")
            }) { Text("Update song") }
        }
    }
}