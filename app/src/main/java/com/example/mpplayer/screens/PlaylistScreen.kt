package com.example.mpplayer.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mpplayer.BottomBar
import com.example.mpplayer.PlayerBar
import com.example.mpplayer.SongView
import com.example.mpplayer.view.models.PlayerViewModel
import com.example.mpplayer.view.models.PlaylistSongsViewModel
import com.example.mpplayer.view.models.PlaylistViewModel
import com.example.mpplayer.view.models.SongViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistScreen(
    navController: NavController,
    songViewModel: SongViewModel,
    playlistViewModel: PlaylistViewModel,
    playlistSongsViewModel: PlaylistSongsViewModel,
    playerViewModel: PlayerViewModel,
    playlistId: Long
) {
    playlistSongsViewModel.getAllSongsFromPlaylist(playlistId)
    val songIds by playlistSongsViewModel.songIds.observeAsState(emptyList())
    songViewModel.getSongsByIds(songIds)
    val songs by songViewModel.songsList.observeAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("List of all songs")
                }
            )
        },
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(songs) { song ->
//                Log.i("Song ${song.songId}", "${song.title}, ${song.artist}")
                    SongView(song, navController, playerViewModel)
                }
            }
            PlayerBar(
                playerViewModel = playerViewModel,
                navController = navController,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}