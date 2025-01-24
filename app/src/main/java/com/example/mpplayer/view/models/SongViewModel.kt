package com.example.mpplayer.view.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mpplayer.dao.SongDao
import com.example.mpplayer.tables.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SongViewModel(private val songDao: SongDao) : ViewModel() {
    private val _songsList = MutableLiveData<List<Song>>()
    val songsList: MutableLiveData<List<Song>> get() = _songsList
    private val _selectedSong = MutableLiveData<Song>()
    val selectedSong: LiveData<Song> = _selectedSong

    fun addFakeSongs() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                for (i in 0..5) {
                    songDao.insertSong(
                        Song(
                            title = "xdx",
                            description = "dxd",
                            rating = 2,
                            artist = "Jack Sparrow",
                            album = "SIki mik",
                            genre = "pop lok",
                            imagePath = "dadad",
                            songPath = "fasfasfsa",
                        )
                    )
                }
                getAllSongs()
            }
        }
    }

    fun getSong(songId: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _selectedSong.postValue(songDao.getSongById(songId))
            }
        }
    }

    fun getSelectedSongs(songIds: MutableLiveData<List<Long>>) {
        var songs: MutableList<Song> = mutableListOf()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                songIds.value?.forEach { songId ->
                    songs.add(songDao.getSongById(songId))
                }
            }
        }
        _songsList.postValue(songs)
    }

    fun getAllSongs() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _songsList.postValue(listOf())
                _songsList.postValue(songDao.getAllSongs())
            }
        }
    }
}