package com.example.mpplayer.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mpplayer.tables.Playlist

@Dao
interface PlaylistDao {
    @Insert()
    fun insertPlaylist(playlist: Playlist)

    @Delete
    fun deletePlaylist(playlist: Playlist)

    @Update
    fun updatePlaylist(playlist: Playlist)
    
    @Query("SELECT * FROM playlist_table")
    fun getAllPlaylists(): List<Playlist>
}