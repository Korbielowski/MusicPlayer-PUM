package com.example.mpplayer.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "playlist_table")
data class Playlist(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "playlist_id")
    val playlistId: Long = 0,
    @ColumnInfo(name = "playlist_title")
    val title: String,
    @ColumnInfo(name = "playlist_description")
    val description: String,
    @ColumnInfo(name = "playlist_rating")
    val rating: Int,
    @ColumnInfo(name = "playlist_image_path")
    val imagePath: String
)
