package com.example.mpplayer

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mpplayer.screens.MainScreen
import com.example.mpplayer.view.models.PlaylistViewModelModel
import com.example.mpplayer.view.models.SongViewModel

@Composable
fun MPPNavigation(songViewModel: SongViewModel, playListViewModel: PlaylistViewModelModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "mainScreen") {
        composable("mainScreen") {
            MainScreen(navController, songViewModel, playListViewModel)
        }
        composable("songsScreen") { }
        composable("songScreen/{songId}") { }
        composable("playListsScreen") { }
        composable("playListScreen/{playlistId}") { }
    }
}