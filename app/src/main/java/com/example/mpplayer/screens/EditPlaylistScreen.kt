package com.example.mpplayer.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPlaylistScreen(
    navController: NavController,
    playlistViewModel: PlaylistViewModel,
    playlistSongsViewModel: PlaylistSongsViewModel,
    playerViewModel: PlayerViewModel,
    playlistId: Long
) {
    playlistViewModel.getPlaylist(playlistId)
    val playlist by playlistViewModel.selectedPlaylist.observeAsState()
    var title by remember { mutableStateOf(playlist?.title) }
    var description by remember { mutableStateOf(playlist?.description) }
    var rating by remember { mutableStateOf(playlist?.rating) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Edit playlist: $title")
                }
            )
        },
        bottomBar = { BottomBar(navController = navController) }) { innerPadding ->
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
            Slider(
                value = rating?.toFloat() ?: 0f,
                onValueChange = { rating = it.toInt() },
                valueRange = 1f..5f,
                steps = 3
            )
            Button(onClick = {
                playlistViewModel.editPlaylist(
                    playlistId = playlistId,
                    title = title ?: "",
                    description = description ?: "",
                    rating = rating ?: 0,
                )
                navController.navigate("mainScreen")
            }) { Text("Edit playlist") }
            Button(onClick = {
                playlistViewModel.deletePlaylist(playlistId)
                playlistSongsViewModel.deletePlaylist(playlistId)
                navController.navigate("mainScreen")
            }) { Text("Delete playlist") }
        }
    }
}