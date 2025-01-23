package com.example.mpplayer.dao

import androidx.room.Insert
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Update
import com.example.mpplayer.tables.PlaylistSongs

@Dao
interface PlaylistSongsDao {
    @Insert()
    fun insertPlaylistSongs(playlistSongs: PlaylistSongs)

    @Delete
    fun deletePlaylistSongs(playlistSongs: PlaylistSongs)

    @Update
    fun updatePlaylistSongs(playlistSongs: PlaylistSongs)
}