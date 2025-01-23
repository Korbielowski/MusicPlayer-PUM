package com.example.mpplayer.view.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mpplayer.dao.PlaylistDao
import com.example.mpplayer.dao.SongDao
import com.example.mpplayer.tables.Playlist
import com.example.mpplayer.tables.Song

class PlaylistViewModelModel( private val playlistDao: PlaylistDao): ViewModel() {
    private val _playlistsList: LiveData<List<Playlist>> = playlistDao.getAllPlaylists()
    val playlistsList: LiveData<List<Playlist>> get() = _playlistsList


}
