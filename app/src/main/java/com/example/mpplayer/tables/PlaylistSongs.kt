package com.example.mpplayer.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "playlist_songs_table",
    foreignKeys = [
        ForeignKey(entity = Playlist::class,
            parentColumns = arrayOf("playlist_id"),
            childColumns = arrayOf("playlist_id"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class PlaylistSongs(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "playlist_songs_id")
    val playlistSongsId: Long,
    @ColumnInfo(name = "playlist_id")
    val playlistId: Long,
    @ColumnInfo(name = "song_id")
    val songId: Long,
    )
