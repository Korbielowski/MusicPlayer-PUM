package com.example.mpplayer.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs_table")
data class Song(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "song_id")
    val songId: Long = 0,
    @ColumnInfo(name = "song_title")
    val title: String,
    @ColumnInfo(name = "song_description")
    val description: String,
    @ColumnInfo(name = "song_rating")
    val rating: Int,
    @ColumnInfo(name = "song_artist")
    val artist: String,
    @ColumnInfo(name = "song_album")
    val album: String,
    @ColumnInfo(name = "song_genre")
    val genre: String,
    @ColumnInfo(name = "song_image_path")
    val imagePath: String,
    @ColumnInfo(name = "song_path")
    val songPath: String,
)
