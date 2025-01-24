package com.example.mpplayer.view.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mpplayer.dao.PlaylistDao
import com.example.mpplayer.tables.Playlist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlaylistViewModel(private val playlistDao: PlaylistDao) : ViewModel() {
    private val _playlistsList = MutableLiveData<List<Playlist>>()
    val playlistsList: MutableLiveData<List<Playlist>> get() = _playlistsList

    fun getAllPlaylists() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _playlistsList.postValue(playlistDao.getAllPlaylists())
            }
        }
    }

    fun addFakePlaylists() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                for (i in 0..20) {
                    playlistDao.insertPlaylist(
                        Playlist(
                            title = "xdx",
                            description = "dxd",
                            rating = 2,
                            imagePath = "dadad",
                        )
                    )
                }
                getAllPlaylists()
            }
        }
    }
}
