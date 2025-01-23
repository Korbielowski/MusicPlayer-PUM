package com.example.mpplayer.view.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mpplayer.dao.SongDao
import com.example.mpplayer.tables.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SongViewModel( private val songDao: SongDao): ViewModel() {
    private val _songsList: LiveData<List<Song>> = songDao.getAllSongs()
    val songsList: LiveData<List<Song>> get() = _songsList

//    private fun getAllSongs() : LiveData<List<Song>>{
//        viewModelScope.launch {
//            withContext(Dispatchers.IO){
//                songDao.getAllSongs()
//            }
//        }
//    }
}