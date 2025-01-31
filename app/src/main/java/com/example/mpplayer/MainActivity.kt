package com.example.mpplayer

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_AUDIO
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.example.mpplayer.ui.theme.MPPlayerTheme
import com.example.mpplayer.view.models.PlayerViewModel
import com.example.mpplayer.view.models.PlaylistSongsViewModel
import com.example.mpplayer.view.models.PlaylistViewModel
import com.example.mpplayer.view.models.SongViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (ContextCompat.checkSelfPermission(
                    applicationContext,
                    READ_MEDIA_AUDIO
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                Log.d("Permissions", "Permission granted.")
                // Proceed with your logic
            } else {
                // Request permission if not granted
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(READ_MEDIA_AUDIO),
                    200
                )
            }
        } else {
            // For older versions of Android (pre-11)
            if (ContextCompat.checkSelfPermission(
                    applicationContext,
                    READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                Log.d("Permissions", "Permission granted.")
                // Proceed with your logic
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(READ_EXTERNAL_STORAGE),
                    200
                )
            }
        }


        val database = Room.databaseBuilder(
            applicationContext,
            MPPlayerDatabase::class.java,
            "mpp_player_database"
        ).build()// MPPlayerDatabase.getDatabase(this)
        val songViewModel = SongViewModel(database.songDao())
        songViewModel.getAllSongs()
        val playlistViewModel = PlaylistViewModel(database.playlistDao())
        playlistViewModel.getAllPlaylists()
        val playlistSongsViewModel = PlaylistSongsViewModel(database.playlistSongsDao())
        playlistSongsViewModel.getAllPlaylistSongs()
        val playerViewModel = PlayerViewModel(application)
        setContent {
            MPPlayerTheme {
                MPPNavigation(
                    songViewModel = songViewModel,
                    playlistViewModel = playlistViewModel,
                    playlistSongsViewModel = playlistSongsViewModel,
                    playerViewModel = playerViewModel,
                    application = application
                )
            }
        }
    }
}

//fun getSongsFromDirectories(context: Context) {
////    Environment.getExternalStorageDirectory().absolutePath
////    audioReader
//    context.contentResolver.query(
//        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
//
//        )?.use { cursor ->
//
//    }
//    var audioFiles = MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
//    audioFiles.
//}