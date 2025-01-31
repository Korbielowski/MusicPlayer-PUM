package com.example.mpplayer.view.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.mpplayer.tables.Song

enum class SongState {
    PLAYING,
    PAUSED,
    STOPPED,
    IN_FOCUS_PLAYING,
    IN_FOCUS_PAUSED,
}

class PlayerViewModel(application: Application) : AndroidViewModel(application) {
    private var exoPlayer: ExoPlayer? = null
    var mSong: MutableLiveData<Song?> = MutableLiveData(null)
    var isPlaying = MutableLiveData(SongState.STOPPED)

    init {
        exoPlayer = ExoPlayer.Builder(application).build()
    }

    fun playSong(song: Song) {
        mSong.postValue(song)
        val mediaItem = MediaItem.fromUri(song.songPath)
        exoPlayer?.setMediaItem(mediaItem)
        exoPlayer?.prepare()
        exoPlayer?.play()
        changeSongState(SongState.PLAYING)
    }

    private fun resume() {
        exoPlayer?.play()
        if (isPlaying.value == SongState.IN_FOCUS_PAUSED) {
            changeSongState(SongState.IN_FOCUS_PLAYING)
        } else {
            changeSongState(SongState.PLAYING)
        }
    }

    private fun pauseSong() {
        exoPlayer?.pause()
        if (isPlaying.value == SongState.IN_FOCUS_PLAYING) {
            changeSongState(SongState.IN_FOCUS_PAUSED)
        } else {
            changeSongState(SongState.PAUSED)
        }
    }

    fun stopSong() {
        exoPlayer?.stop()
        changeSongState(SongState.STOPPED)
    }

    fun togglePlayPause() {
        if (isPlaying.value == SongState.PLAYING) {
            pauseSong()
        } else {
            resume()
        }
    }

    override fun onCleared() {
        super.onCleared()
        mSong.postValue(null)
        isPlaying.postValue(SongState.STOPPED)
        exoPlayer?.release()
    }

    fun changeSongState(songState: SongState) {
        isPlaying.postValue(songState)
    }

    fun getPlayer() = exoPlayer

}