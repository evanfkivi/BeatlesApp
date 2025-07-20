package com.example.beatlesapp.data

import androidx.annotation.DrawableRes
import com.example.beatlesapp.R

data class AlbumItem(
    val title: String,
    val year: Int,
    val songs: Int,
    val length: String,
    @DrawableRes val art: Int,
)

val AlbumList = listOf(
    AlbumItem("Abbey Road", 1969, 17, "47 min 29 sec", R.drawable.abbeyroad),
    AlbumItem("Rubber Soul", 1965, 14, "35 min 32 sec", R.drawable.rubbersoul),
    AlbumItem("Revolver", 1966, 14, "34 min 45 sec", R.drawable.revolver),
    AlbumItem("Sgt. Pepper's Lonely Hearts Club Band", 1967, 13, "39 min 55 sec", R.drawable.sgtpepper),
)
