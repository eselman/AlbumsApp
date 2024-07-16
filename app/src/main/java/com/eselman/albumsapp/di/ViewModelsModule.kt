package com.eselman.albumsapp.di

import com.eselman.albumsapp.database.dao.AlbumDao
import com.eselman.albumsapp.network.AlbumsService
import com.eselman.albumsapp.repository.AlbumRepository
import com.eselman.albumsapp.repository.AlbumRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelsModule {
    @Provides
    fun provideAlbumRepositoryImpl(
        albumDao: AlbumDao,
        albumsService: AlbumsService
    ): AlbumRepository =
        AlbumRepositoryImpl(albumDao, albumsService)
}