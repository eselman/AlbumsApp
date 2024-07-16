package com.eselman.albumsapp.network

import com.eselman.albumsapp.model.AlbumsResponse
import retrofit2.http.GET

interface AlbumsService {
    @GET("api/v2/us/music/most-played/100/albums.json")
    suspend fun getAllAlbums(): AlbumsResponse
}
