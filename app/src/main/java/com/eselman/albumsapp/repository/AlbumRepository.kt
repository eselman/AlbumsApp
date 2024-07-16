package com.eselman.albumsapp.repository

import com.eselman.albumsapp.database.entities.AlbumRealm
import com.eselman.albumsapp.model.AlbumState

interface AlbumRepository {
    suspend fun getAlbums(): AlbumState
    suspend fun refreshAlbums(): AlbumState
    suspend fun getAlbumById(albumId: String): AlbumRealm?
}