package com.example.mpplayer.view.models

import androidx.lifecycle.LiveData
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
    private val _selectedPlaylist = MutableLiveData<Playlist>()
    val selectedPlaylist: LiveData<Playlist> = _selectedPlaylist

    fun getAllPlaylists() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _playlistsList.postValue(playlistDao.getAllPlaylists())
            }
        }
    }

    fun addPlaylist(title: String, description: String, rating: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                playlistDao.insertPlaylist(
                    Playlist(
                        title = title,
                        description = description,
                        rating = rating,
                        imagePath = ""
                    )
                )
            }
        }
        getAllPlaylists()
    }

    fun addFakePlaylists() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val titles =
                    listOf("Chill Vibes", "Party Hits", "Relaxing Beats", "Top 50", "Workout Tunes")
                val descriptions = listOf(
                    "The best tracks to chill out",
                    "Get the party started with these hits",
                    "Unwind with these relaxing songs",
                    "The hottest tracks right now",
                    "Boost your workout sessions"
                )

                for (i in 1..5) {
                    val randomTitle = titles.random()
                    val randomDescription = descriptions.random()
                    val randomRating = (1..5).random()

                    playlistDao.insertPlaylist(
                        Playlist(
                            title = "$randomTitle #$i",
                            description = randomDescription,
                            rating = randomRating,
                            imagePath = "path/to/playlist_image_$i.png"
                        )
                    )
                }
                getAllPlaylists()
            }
        }
    }

    fun getPlaylist(playlistId: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _selectedPlaylist.postValue(playlistDao.getPlaylistById(playlistId))
            }
        }
    }

    fun editPlaylist(
        playlistId: Long,
        title: String,
        description: String,
        rating: Int,
    ) {
        val playlist = Playlist(
            playlistId = playlistId,
            title = title,
            description = description,
            rating = rating,
            imagePath = _selectedPlaylist.value?.imagePath ?: ""
        )
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                playlistDao.updatePlaylist(playlist)
            }
        }
    }

    fun deletePlaylist(playlistId: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                playlistDao.deletePlaylistById(playlistId)
                _playlistsList.postValue(playlistDao.getAllPlaylists())
            }
        }
    }
}
