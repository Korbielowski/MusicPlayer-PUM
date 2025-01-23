package com.example.mpplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.room.Room
import com.example.mpplayer.ui.theme.MPPlayerTheme
import com.example.mpplayer.view.models.PlaylistViewModelModel
import com.example.mpplayer.view.models.SongViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val database = Room.databaseBuilder(
            applicationContext,
            MPPlayerDatabase::class.java,
            "mpp_player_database"
        ).build()// MPPlayerDatabase.getDatabase(this)
        val songViewModel = SongViewModel(database.songDao())
        val playlistViewModel = PlaylistViewModelModel(database.playlistDao())
        setContent {
            MPPlayerTheme {
                MPPNavigation(songViewModel, playlistViewModel)
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