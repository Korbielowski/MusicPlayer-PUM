package com.example.mpplayer.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mpplayer.BottomBar
import com.example.mpplayer.PlayerBar
import com.example.mpplayer.R
import com.example.mpplayer.view.models.PlayerViewModel
import com.example.mpplayer.view.models.PlaylistSongsViewModel
import com.example.mpplayer.view.models.PlaylistViewModel
import com.example.mpplayer.view.models.SongViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSongToPlaylist(
    navController: NavController,
    songViewModel: SongViewModel,
    playlistViewModel: PlaylistViewModel,
    playlistSongsViewModel: PlaylistSongsViewModel,
    playerViewModel: PlayerViewModel,
    songId: Long
) {
    val playlists by playlistViewModel.playlistsList.observeAsState(emptyList())
    songViewModel.getSong(songId)
    val song by songViewModel.selectedSong.observeAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Add ${song?.title} to a playlist")
                }
            )
        },
        bottomBar = { BottomBar(navController = navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(playlists) { playlist ->
                    Box(
                        modifier = Modifier
                            .border(1.dp, Color.Red)
                            .fillMaxWidth()
                            .clickable {
                                playlistSongsViewModel.addSongToPlaylist(
                                    songId = songId,
                                    playlistId = playlist.playlistId
                                )
                                navController.navigate("mainScreen")
                            }
                    ) {
                        Row {
                            Image(
                                modifier = Modifier.size(100.dp, 100.dp),
                                painter = painterResource(id = R.drawable.tux_mascot),
                                contentDescription = "${playlist.title}"
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text("Playlist title: ${playlist.title}")
                                Text("Rating: ${playlist.rating}")
                                // TODO: Place Stars icons based on song.rating
                            }
                            Spacer(modifier = Modifier.width(30.dp))
                        }
                    }
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