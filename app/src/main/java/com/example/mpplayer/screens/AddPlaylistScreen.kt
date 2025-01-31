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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mpplayer.BottomBar
import com.example.mpplayer.view.models.PlaylistViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPlaylistScreen(
    navController: NavController,
    playlistViewModel: PlaylistViewModel,
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var rating by remember { mutableIntStateOf(0) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Add new playlist")
                }
            )
        },
        bottomBar = { BottomBar(navController = navController) }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Enter song's name") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Enter song's description") },
                modifier = Modifier.fillMaxWidth()
            )
            Slider(
                value = rating.toFloat(),
                onValueChange = { rating = it.toInt() },
                valueRange = 1f..5f,
                steps = 3
            )
            Button(onClick = {
                playlistViewModel.addPlaylist(
                    title = title,
                    description = description,
                    rating = rating,
                )
                navController.navigate("mainScreen")
            }) { Text("Create new playlist") }
        }
    }
}