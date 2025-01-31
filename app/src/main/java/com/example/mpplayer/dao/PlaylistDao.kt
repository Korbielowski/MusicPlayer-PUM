package com.example.mpplayer.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mpplayer.tables.Playlist

@Dao
interface PlaylistDao {
    @Insert
    suspend fun insertPlaylist(playlist: Playlist)

    @Delete
    suspend fun deletePlaylist(playlist: Playlist)

    @Update
    suspend fun updatePlaylist(playlist: Playlist)

    @Query("SELECT * FROM playlist_table")
    suspend fun getAllPlaylists(): List<Playlist>

    @Query("SELECT * FROM playlist_table WHERE playlist_id = :playlistId")
    suspend fun getPlaylistById(playlistId: Long): Playlist

    @Query("DELETE FROM playlist_table WHERE playlist_id = :playlistId")
    suspend fun deletePlaylistById(playlistId: Long)
}