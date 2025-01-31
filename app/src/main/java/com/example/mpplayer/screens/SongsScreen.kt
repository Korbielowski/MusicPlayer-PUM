package com.example.mpplayer.screens

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mpplayer.BottomBar
import com.example.mpplayer.PlayerBar
import com.example.mpplayer.SongView
import com.example.mpplayer.addSongs
import com.example.mpplayer.view.models.PlayerViewModel
import com.example.mpplayer.view.models.PlaylistSongsViewModel
import com.example.mpplayer.view.models.PlaylistViewModel
import com.example.mpplayer.view.models.SongViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.R)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongsScreen(
    navController: NavController,
    songViewModel: SongViewModel,
    playlistViewModel: PlaylistViewModel,
    playlistSongsViewModel: PlaylistSongsViewModel,
    playerViewModel: PlayerViewModel,
    application: Application
) {
    songViewModel.getAllSongs()
    val songsList by songViewModel.songsList.observeAsState(emptyList())
    val coroutineScope = rememberCoroutineScope()
//    val playlists by playlistViewModel.playlistsList.observeAsState(emptyList())
//    val playlistSongs by playlistSongsViewModel.playlistSongsList.observeAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("List of all songs")
                },
                actions = {
                    Button(onClick = {
                        coroutineScope.launch {
                            addSongs(
                                songViewModel = songViewModel,
                                application = application
                            )
                        }
                    }) {
                        Icon(
                            Icons.Rounded.Add,
                            contentDescription = "Add songs"
                        )
                    }
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
                items(songsList) { song ->
                    SongView(song, navController, playerViewModel)
                }
            }
            PlayerBar(
                playerViewModel = playerViewModel,
                navController = navController,
                modifier = Modifier.align(
                    Alignment.BottomCenter
                )
            )
        }
    }
}