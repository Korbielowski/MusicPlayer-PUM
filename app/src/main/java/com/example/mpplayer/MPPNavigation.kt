package com.example.mpplayer

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mpplayer.screens.AddSongToPlaylist
import com.example.mpplayer.screens.MainScreen
import com.example.mpplayer.screens.PlaylistScreen
import com.example.mpplayer.screens.SongsScreen
import com.example.mpplayer.view.models.PlaylistSongsViewModel
import com.example.mpplayer.view.models.PlaylistViewModel
import com.example.mpplayer.view.models.SongViewModel

@Composable
fun MPPNavigation(
    songViewModel: SongViewModel,
    playlistViewModel: PlaylistViewModel,
    playlistSongsViewModel: PlaylistSongsViewModel
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "mainScreen") {
        composable("mainScreen") {
            MainScreen(
                navController = navController,
                songViewModel = songViewModel,
                playlistViewModel = playlistViewModel
            )
        }
        composable("songsScreen") {
            SongsScreen(
                navController = navController,
                songViewModel = songViewModel,
                playlistViewModel = playlistViewModel,
                playlistSongsViewModel = playlistSongsViewModel,
            )
        }
        composable(
            "addSongToPlaylist/{songId}",
            arguments = listOf(navArgument("songId") { type = NavType.LongType })
        ) { backStackEntry ->
            val songId = backStackEntry.arguments?.getLong("songId") ?: 0L
            AddSongToPlaylist(
                navController = navController,
                songViewModel = songViewModel,
                playlistViewModel = playlistViewModel,
                playlistSongsViewModel = playlistSongsViewModel,
                songId = songId,
            )
        }
        composable("songScreen/{songId}") { }
        composable("playListsScreen") { }
        composable(
            "playListScreen/{playlistId}",
            arguments = listOf(navArgument("playlistId") { type = NavType.LongType })
        ) { backStackEntry ->
            val playlistId = backStackEntry.arguments?.getLong("playlistId") ?: 0L
            PlaylistScreen(
                navController = navController,
                songViewModel = songViewModel,
                playlistViewModel = playlistViewModel,
                playlistSongsViewModel = playlistSongsViewModel,
                playlistId = playlistId,
            )
        }
    }
}