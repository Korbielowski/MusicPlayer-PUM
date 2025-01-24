package com.example.mpplayer.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mpplayer.R
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
    playlistId: Long
) {
    playlistSongsViewModel.getAllSongsFromPlaylist(playlistId)
    val playlistSongs by playlistSongsViewModel.songIds.observeAsState(emptyList())
    val song by songViewModel.selectedSong.observeAsState()
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
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(playlistSongs.size) { index ->
                val playlistSong = playlistSongs[index]
                songViewModel.getSong(playlistSong.songId)
                Box(
                    modifier = Modifier
                        .border(1.dp, Color.Red)
                        .fillMaxWidth()
                ) {
                    Row() {
                        Image(
                            modifier = Modifier.size(100.dp, 100.dp),
                            painter = painterResource(id = R.drawable.subliming),
                            contentDescription = "${song?.title}"
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text("Title: ${song?.title}")
                            Text("Album: ${song?.album}")
                            Text("Artist: ${song?.artist}")
                            Text("Genre: ${song?.genre}")
                            // TODO: Place Stars icons based on song.rating
                        }
                        Spacer(modifier = Modifier.width(30.dp))
                        Button(onClick = {
                            navController.navigate("addSongToPlaylist/${song?.songId}")
                        }) {
                            Icon(
                                Icons.Rounded.Add,
                                contentDescription = "Add song to a playlist"
                            )
                        }
                    }
                }
            }
        }
    }
}