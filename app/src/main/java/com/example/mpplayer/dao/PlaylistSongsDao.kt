package com.example.mpplayer.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mpplayer.tables.PlaylistSongs

@Dao
interface PlaylistSongsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPlaylistSongs(playlistSongs: PlaylistSongs)

    @Delete
    fun deletePlaylistSongs(playlistSongs: PlaylistSongs)

    @Update
    fun updatePlaylistSongs(playlistSongs: PlaylistSongs)

    @Query("SELECT * FROM playlist_songs_table WHERE playlist_id = :playlistId")
    fun getAllSongsFromPlayList(playlistId: Long): List<PlaylistSongs>

    @Query("SELECT * FROM playlist_songs_table")
    fun getAllPlaylistSongs(): List<PlaylistSongs>
}