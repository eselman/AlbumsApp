package com.eselman.albumsapp.database.dao

import com.eselman.albumsapp.database.entities.AlbumRealm

interface AlbumDao {
    suspend fun insertAlbums(albums: List<AlbumRealm>)
    suspend fun getAllAlbums(): List<AlbumRealm>
    suspend fun getAlbumById(id: String): AlbumRealm?
    suspend fun deleteAllAlbums()
}