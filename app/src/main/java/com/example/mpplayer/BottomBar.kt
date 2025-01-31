package com.example.mpplayer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController

@Composable
fun BottomBar(navController: NavController) {
    val selectedItem = remember { mutableIntStateOf(0) }
    NavigationBar {
        NavigationBarItem(icon = {
            Icon(
                Icons.Rounded.Home,
                contentDescription = "Playlists"
            )
        },
            label = { Text("Playlists") },
            selected = selectedItem.value == 0,
            onClick = {
                selectedItem.value = 0
                navController.navigate("mainScreen")
            })
        NavigationBarItem(icon = {
            Icon(
                Icons.Default.PlayArrow,
                contentDescription = "Songs"
            )
        },
            label = { Text("Songs") },
            selected = selectedItem.value == 1,
            onClick = {
                selectedItem.value = 1
                navController.navigate("songsScreen")
            })
    }
}