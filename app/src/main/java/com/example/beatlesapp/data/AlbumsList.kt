package com.example.beatlesapp.data

data class AlbumItem(
    val title: String,
    val year: Int,
    val songs: Int,
    val length: String
)

val AlbumList = listOf(
    AlbumItem("Abbey Road", 1969, 17, "47 min 29 sec"),
    AlbumItem("Rubber Soul", 1965, 14, "35 min 32 sec"),
    AlbumItem("Revolver", 1966, 14, "34 min 45 sec"),
    AlbumItem("Sgt. Pepper's Lonely Hearts Club Band", 1967, 13, "39 min 55 sec"),
    AlbumItem("Let It Be", 1970, 12, "35 min 10 sec")
)