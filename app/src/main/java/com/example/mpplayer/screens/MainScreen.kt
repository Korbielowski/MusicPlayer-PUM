package com.example.mpplayer.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.mpplayer.view.models.PlaylistViewModel
import com.example.mpplayer.view.models.SongViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    songViewModel: SongViewModel,
    playlistViewModel: PlaylistViewModel
) {
    val songsList by songViewModel.songsList.observeAsState(emptyList())
    val playlistsList by playlistViewModel.playlistsList.observeAsState(emptyList())
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("List of all playlists")
                }
            )
        },
        bottomBar = {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { navController.navigate("songsScreen") }) { Text("All songs") }
        }
    ) { innerPadding ->
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalItemSpacing = 10.dp,
        ) {
            items(playlistsList.size) { index ->
                val playlist = playlistsList[index]
                Box(
                    modifier = Modifier
                        .border(1.dp, Color.Red)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("playlistScreen/${playlist.playlistId}")
                        }
                ) {
                    Row() {
                        Image(
                            modifier = Modifier.size(100.dp, 100.dp),
                            painter = painterResource(id = R.drawable.tux_mascot),
                            contentDescription = "${playlist.title}"
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text("Playlist title: ${playlist.title}")
                            Text("Rating: ${playlist.rating}")
                            Text("Id: ${playlist.playlistId}")
                            // TODO: Place Stars icons based on song.rating
                        }
                        Spacer(modifier = Modifier.width(30.dp))
                    }
                }
            }
        }
    }
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(20.dp),
//        verticalArrangement = Arrangement.spacedBy(30.dp)
//    ) {
//        Button(
//            onClick = {
//                navController.navigate("songsScreen")
//            }, modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            Text("See all Your songs")
//        }
//        Button(
//            onClick = {
//                navController.navigate("playListsScreen")
//            }, modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            Text("See all Your playlists")
//        }
//        Button(
//            onClick = {
//                songViewModel.addFakeSongs()
//            }, modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            Text("Add fake songs")
//        }
//        Button(
//            onClick = {
//                playlistViewModel.addFakePlaylists()
//            }, modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            Text("Add fake playlists")
//        }
//    }
}
