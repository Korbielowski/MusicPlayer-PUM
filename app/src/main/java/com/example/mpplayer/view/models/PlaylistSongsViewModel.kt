package com.example.mpplayer.view.models


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mpplayer.dao.PlaylistSongsDao
import com.example.mpplayer.tables.PlaylistSongs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlaylistSongsViewModel(private val playlistSongsDao: PlaylistSongsDao) : ViewModel() {
    private val _songIds = MutableLiveData<List<Int>>()
    val songIds: MutableLiveData<List<Int>> get() = _songIds
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
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _songIds.postValue(playlistSongsDao.getAllSongsFromPlayList(playlistId))
            }
        }
    }

    fun deletePlaylist(playlistId: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                playlistSongsDao.deletePlaylistSongsById(playlistId)
                getAllPlaylistSongs()
            }
        }
    }

    fun deleteSong(songId: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                playlistSongsDao.deleteSongById(songId)
                getAllPlaylistSongs()
            }
        }
    }
}