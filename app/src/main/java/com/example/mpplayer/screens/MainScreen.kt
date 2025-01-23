package com.example.mpplayer.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mpplayer.view.models.PlaylistViewModelModel
import com.example.mpplayer.view.models.SongViewModel

@Composable
fun MainScreen(
    navController: NavController,
    songViewModel: SongViewModel,
    playlistViewModelModel: PlaylistViewModelModel
) {
    val songsList by songViewModel.songsList.observeAsState(emptyList())
    val playlistsList by playlistViewModelModel.playlistsList.observeAsState(emptyList())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Button(
            onClick = {
                navController.navigate("songsScreen")
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("See all Your songs")
        }
        Button(onClick = {
            navController.navigate("playListsScreen")
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            Text("See all Your playlists")
        }
    }
}