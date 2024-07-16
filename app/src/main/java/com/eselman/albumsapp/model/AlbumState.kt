package com.eselman.albumsapp.model

import com.eselman.albumsapp.database.entities.AlbumRealm

data class AlbumState(
    val albums: List<AlbumRealm>?,
    val isError: Boolean
)