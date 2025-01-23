package com.example.mpplayer

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mpplayer.dao.PlaylistDao
import com.example.mpplayer.dao.PlaylistSongsDao
import com.example.mpplayer.dao.SongDao
import com.example.mpplayer.tables.Playlist
import com.example.mpplayer.tables.PlaylistSongs
import com.example.mpplayer.tables.Song

@Database(entities = [Song::class, Playlist::class, PlaylistSongs::class], version = 1)
abstract class MPPlayerDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao
    abstract fun playlistDao(): PlaylistDao
    abstract fun playlistSongsDao(): PlaylistSongsDao

//    companion object {
//        @Volatile
//        private var INSTANCE: MPPlayerDatabase? = null
//
//        fun getDatabase(context: Context): MPPlayerDatabase{
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    MPPlayerDatabase::class.java,
//                    "mpp_player_database"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
}