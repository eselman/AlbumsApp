package com.eselman.albumsapp.repository

import com.eselman.albumsapp.database.dao.AlbumDao
import com.eselman.albumsapp.database.entities.AlbumRealm
import com.eselman.albumsapp.model.AlbumState
import com.eselman.albumsapp.network.AlbumsService
import com.eselman.albumsapp.utils.toAlbumRealm
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val albumDao: AlbumDao,
    private val albumsService: AlbumsService
): AlbumRepository {
    override suspend fun getAlbums(): AlbumState {
        val albumsFromDatabase = albumDao.getAllAlbums()

        val albumState = if (albumsFromDatabase.isEmpty()) {
            refreshAlbums()
        } else {
            AlbumState(
                albums = albumsFromDatabase,
                isError = false
            )
        }
        return albumState
    }

    override suspend fun refreshAlbums(): AlbumState {
        val albumState = try {
            // Get Albums from Service
            val albumsFromService = albumsService.getAllAlbums()
            if (albumsFromService.feed.albums.isNotEmpty()) {
                val albums = albumsFromService.feed.albums.map { album ->
                    album.toAlbumRealm(albumsFromService.feed.copyright ?: "")
                }

                // Clear all database records
                albumDao.deleteAllAlbums()

                // Insert new records in the database
                albumDao.insertAlbums(albums)
                AlbumState(
                    albums = albums,
                    isError = false
                )
            } else {
                AlbumState(
                    albums = null,
                    isError = true
                )
            }
        } catch (e: Exception) {
            AlbumState(
                albums = null,
                isError = true
            )
        }

        return albumState
    }

    override suspend fun getAlbumById(albumId: String): AlbumRealm? = albumDao.getAlbumById(albumId)
}