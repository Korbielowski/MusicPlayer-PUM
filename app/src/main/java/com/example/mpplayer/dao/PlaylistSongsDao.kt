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

    @Query("SELECT song_id FROM playlist_songs_table WHERE playlist_id = :playlistId")
    fun getAllSongsFromPlayList(playlistId: Long): List<Int>

    @Query("SELECT * FROM playlist_songs_table")
    fun getAllPlaylistSongs(): List<PlaylistSongs>

    @Query("DELETE FROM playlist_songs_table WHERE playlist_id = :playlistId")
    suspend fun deletePlaylistSongsById(playlistId: Long)

    @Query("DELETE FROM playlist_songs_table WHERE song_id = :songId")
    suspend fun deleteSongById(songId: Long)
}