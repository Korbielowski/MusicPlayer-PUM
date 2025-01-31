package com.example.mpplayer

import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mpplayer.tables.Song
import com.example.mpplayer.view.models.PlayerViewModel

@Composable
fun SongView(song: Song, navController: NavController, playerViewModel: PlayerViewModel) {
    Box(
        modifier = Modifier
            .border(1.dp, Color.Red)
            .fillMaxWidth()
            .clickable { playerViewModel.playSong(song) }
    ) {
        Row {
            BitmapImage(song.imagePath)
            Spacer(modifier = Modifier.width(10.dp))
            Column(Modifier.weight(1f)) {
                Text("Title: ${song.title}")
                Text("Album: ${song.album}")
                Text("Artist: ${song.artist}")
                Text("Genre: ${song.genre}")
                // TODO: Place Stars icons based on song.rating
            }
            Column {
                Button(onClick = {
                    navController.navigate("addSongToPlaylist/${song.songId}")
                }) {
                    Icon(
                        Icons.Rounded.Add,
                        contentDescription = "Add song to a playlist"
                    )
                }
                Button(onClick = {
                    navController.navigate("editSong/${song.songId}")
                }) {
                    Icon(
                        Icons.Rounded.Edit,
                        contentDescription = "Edit song"
                    )
                }
            }
        }
    }
}

@Composable
fun BitmapImage(imagePath: String) {
    val retriever = MediaMetadataRetriever()
    val image = try {
        retriever.setDataSource(imagePath)
        val embeddedPicture = retriever.embeddedPicture
        embeddedPicture?.let { BitmapFactory.decodeByteArray(it, 0, it.size) }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    } finally {
        retriever.release()
    }

    return if (image != null) {
        Image(
            bitmap = image.asImageBitmap(),
            contentDescription = "$imagePath",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(80.dp)
        )
    } else {
        Image(
            painter = painterResource(id = android.R.drawable.ic_dialog_alert),
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(80.dp),
            contentDescription = "No Image"
        )
    }
}