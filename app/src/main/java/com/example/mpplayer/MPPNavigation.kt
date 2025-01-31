package com.example.mpplayer

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mpplayer.screens.AddPlaylistScreen
import com.example.mpplayer.screens.AddSongToPlaylist
import com.example.mpplayer.screens.EditPlaylistScreen
import com.example.mpplayer.screens.EditSongScreen
import com.example.mpplayer.screens.MainScreen
import com.example.mpplayer.screens.PlaylistScreen
import com.example.mpplayer.screens.SongScreen
import com.example.mpplayer.screens.SongsScreen
import com.example.mpplayer.view.models.PlayerViewModel
import com.example.mpplayer.view.models.PlaylistSongsViewModel
import com.example.mpplayer.view.models.PlaylistViewModel
import com.example.mpplayer.view.models.SongViewModel

@Composable
fun MPPNavigation(
    songViewModel: SongViewModel,
    playlistViewModel: PlaylistViewModel,
    playlistSongsViewModel: PlaylistSongsViewModel,
    playerViewModel: PlayerViewModel,
    application: Application
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "mainScreen") {
        composable("mainScreen") {
            MainScreen(
                navController = navController,
                songViewModel = songViewModel,
                playlistViewModel = playlistViewModel,
                playerViewModel = playerViewModel
            )
        }
        composable("songsScreen") {
            SongsScreen(
                navController = navController,
                songViewModel = songViewModel,
                playlistViewModel = playlistViewModel,
                playlistSongsViewModel = playlistSongsViewModel,
                playerViewModel = playerViewModel,
                application = application
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
                playerViewModel = playerViewModel,
                songId = songId,
            )
        }
        composable("songScreen") {
            SongScreen(
                navController = navController,
                playerViewModel = playerViewModel,
                playlistSongsViewModel = playlistSongsViewModel,
                songViewModel = songViewModel
            )
        }
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
                playerViewModel = playerViewModel,
                playlistId = playlistId,
            )
        }
        composable(
            "editSong/{songId}",
            arguments = listOf(navArgument("songId") { type = NavType.LongType })
        ) { backStackEntry ->
            val songId = backStackEntry.arguments?.getLong("songId") ?: 0L
            EditSongScreen(
                navController = navController,
                songViewModel = songViewModel,
                playlistViewModel = playlistViewModel,
                playlistSongsViewModel = playlistSongsViewModel,
                playerViewModel = playerViewModel,
                songId = songId,
            )
        }
        composable("addPlaylistScreen") {
            AddPlaylistScreen(
                navController = navController,
                playlistViewModel = playlistViewModel,
            )
        }
        composable(
            "editPlaylist/{playlistId}",
            arguments = listOf(navArgument("playlistId") { type = NavType.LongType })
        ) { backStackEntry ->
            val playlistId = backStackEntry.arguments?.getLong("playlistId") ?: 0L
            EditPlaylistScreen(
                navController = navController,
                playlistViewModel = playlistViewModel,
                playlistSongsViewModel = playlistSongsViewModel,
                playerViewModel = playerViewModel,
                playlistId = playlistId,
            )
        }
    }
}
