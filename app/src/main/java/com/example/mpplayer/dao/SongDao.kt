package com.example.mpplayer.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.example.mpplayer.tables.Song

@Dao()
interface SongDao {
    @Insert()
    fun insertSong(song: Song)

    @Delete
    fun deleteSong(song: Song)

    @Update
    fun updateSong(song: Song)

    @Query("SELECT * FROM songs_table")
    fun getAllSongs() : LiveData<List<Song>>
//    @Query("UPDATE songs_table SET song_title = :title WHERE song_id = :songId")
//    fun updateTitle(title: String, songId: Long)
//
//    @Query("UPDATE songs_table SET song_description = :description WHERE song_id = :songId")
//    fun updateDescription(description: String, songId: Long)
//
//    @Query("UPDATE songs_table SET song_rating = :rating WHERE song_id = :songId")
//    fun updateRating(rating: String, songId: Long)
//
//    @Query("UPDATE songs_table SET song_artist = :artist WHERE song_id = :songId")
//    fun updateArtist(artist: String, songId: Long)
//
//    @Query("UPDATE songs_table SET song_album = :album WHERE song_id = :songId")
//    fun updateAlbum(album: String, songId: Long)
}