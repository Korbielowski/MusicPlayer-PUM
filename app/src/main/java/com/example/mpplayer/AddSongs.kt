package com.example.mpplayer

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import com.example.mpplayer.tables.Song
import com.example.mpplayer.view.models.SongViewModel

@RequiresApi(Build.VERSION_CODES.R)
@SuppressLint("Range")
fun addSongs(songViewModel: SongViewModel, application: Application) {
    val projection = arrayOf(
        MediaStore.Audio.Media.TITLE,
        MediaStore.Audio.Media.ARTIST,
        MediaStore.Audio.Media.ALBUM,
        MediaStore.Audio.Media.DATA,
        MediaStore.Audio.Media.DURATION
    )
    val contentResolver = application.applicationContext.contentResolver
    val cursor = contentResolver.query(
        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
        projection,
        null,
        null,
        null
    )

    cursor?.use {
        while (it.moveToNext()) {
            val title = it.getString(it.getColumnIndex(MediaStore.Audio.Media.TITLE))
            val artist = it.getString(it.getColumnIndex(MediaStore.Audio.Media.ARTIST))
            val album = it.getString(it.getColumnIndex(MediaStore.Audio.Media.ALBUM))
            val songPath = it.getString(it.getColumnIndex(MediaStore.Audio.Media.DATA))
            val description = it.getString(it.getColumnIndex(MediaStore.Audio.Media.DURATION))
            songViewModel.addSong(
                Song(
                    title = title,
                    description = description,
                    artist = artist,
                    album = album,
                    genre = "genre",
                    songPath = songPath,
                    imagePath = songPath,
                    rating = 0,
                )
            )
        }
    }

    songViewModel.getAllSongs()
}