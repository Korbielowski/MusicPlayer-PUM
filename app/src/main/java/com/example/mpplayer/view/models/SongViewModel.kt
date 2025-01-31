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
                val artists = listOf("Jack Sparrow", "Adele", "Taylor Swift", "Ed Sheeran", "Drake")
                val genres = listOf("Pop", "Rock", "Hip-Hop", "Jazz", "Electronic")
                val albums = listOf(
                    "Best Hits",
                    "Golden Classics",
                    "Summer Vibes",
                    "Top Charts",
                    "Deep Dive"
                )

                for (i in 1..5) {
                    val randomArtist = artists.random()
                    val randomGenre = genres.random()
                    val randomAlbum = albums.random()
                    val randomRating = (1..5).random()

                    songDao.insertSong(
                        Song(
                            title = "Song #$i",
                            description = "Description for song #$i",
                            rating = randomRating,
                            artist = randomArtist,
                            album = randomAlbum,
                            genre = randomGenre,
                            imagePath = "path/to/image_$i.png",
                            songPath = "path/to/song_$i.mp3",
                        )
                    )
                }
                getAllSongs()
            }
        }
    }

    fun editSong(
        songId: Long,
        title: String,
        description: String,
        artist: String,
        album: String,
        genre: String,
        rating: Int,
        playerViewModel: PlayerViewModel
    ) {
        val song = Song(
            songId = songId,
            title = title,
            description = description,
            artist = artist,
            album = album,
            genre = genre,
            rating = rating,
            songPath = selectedSong.value?.songPath ?: "",
            imagePath = selectedSong.value?.imagePath ?: ""
        )
        if (playerViewModel.mSong.value?.songId == songId) {
            playerViewModel.mSong.postValue(song)
        }

//        Log.i("Song", "${song.songId}")
        // TODO: If something goes wrong after edit with playing music, check here !!!!
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                songDao.updateSong(
                    song
                )
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
                _songsList.postValue(songDao.getAllSongs())
            }
        }
    }

    fun getSongsByIds(songIds: List<Int>?) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _songsList.postValue(songDao.getSongsByIds(songIds))
            }
        }
    }

    fun addSong(song: Song) {
        var insert = true
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _songsList.value?.forEach {
                    if (it.title == song.title) {
                        insert = false
                    }
                }
                if (insert) {
                    songDao.insertSong(song)
                } else {
                    insert = true
                }
            }
        }
    }

    fun deleteSong(songId: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                songDao.deleteSongById(songId)
                getAllSongs()
            }
        }
    }
}