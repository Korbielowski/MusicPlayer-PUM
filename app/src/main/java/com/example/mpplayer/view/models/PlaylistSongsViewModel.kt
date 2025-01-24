package com.example.mpplayer.view.models


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mpplayer.dao.PlaylistSongsDao
import com.example.mpplayer.tables.PlaylistSongs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlaylistSongsViewModel(private val playlistSongsDao: PlaylistSongsDao) : ViewModel() {
    private val _songIds = MutableLiveData<List<PlaylistSongs>>()
    val songIds: MutableLiveData<List<PlaylistSongs>> get() = _songIds
    private val _playlistSongsList = MutableLiveData<List<PlaylistSongs>>()
    val playlistSongsList: MutableLiveData<List<PlaylistSongs>> get() = _playlistSongsList

    init {
        getAllPlaylistSongs()
    }

    fun addSongToPlaylist(songId: Long, playlistId: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                playlistSongsDao.insertPlaylistSongs(
                    PlaylistSongs(
                        songId = songId,
                        playlistId = playlistId
                    )
                )
                Log.i("Created new song-playlist connection", "${songId}, $playlistId")
            }
        }
    }

    fun getAllPlaylistSongs() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _playlistSongsList.postValue(playlistSongsDao.getAllPlaylistSongs())
            }
        }
    }

    fun getAllSongsFromPlaylist(playlistId: Long) {
        Log.i("Playlist id", "$playlistId")
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _songIds.postValue(playlistSongsDao.getAllSongsFromPlayList(playlistId))
            }
        }
        Log.i("printing song values", "${_songIds.value?.size}")
        _songIds.value?.forEach { id ->
            Log.i("Song id", "$id")
        }
    }
}