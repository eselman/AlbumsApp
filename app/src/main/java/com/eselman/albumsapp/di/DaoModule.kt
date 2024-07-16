package com.eselman.albumsapp.di

import com.eselman.albumsapp.database.dao.AlbumDao
import com.eselman.albumsapp.database.dao.AlbumDaoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DaoModule {
    @Binds
    abstract fun bindUserDao(impl: AlbumDaoImpl): AlbumDao
}