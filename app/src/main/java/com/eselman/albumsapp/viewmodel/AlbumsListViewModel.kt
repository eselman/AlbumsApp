package com.eselman.albumsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eselman.albumsapp.database.entities.AlbumRealm
import com.eselman.albumsapp.repository.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsListViewModel @Inject constructor(
    private val albumRepository: AlbumRepository
): ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Boolean>()
    val albums = MutableLiveData<List<AlbumRealm>>()

    fun getAlbums() {
        viewModelScope.launch {
            isLoading.value = true
            isError.value = false
            val albumState = albumRepository.getAlbums()
            if (albumState.isError) {
                isError.value = true
                isLoading.value = false
            } else {
                albums.value = albumState.albums ?: emptyList()
                isError.value = false
                isLoading.value = false
            }
        }
    }

    fun refreshAlbums() {
        viewModelScope.launch {
            isLoading.value = true
            isError.value = false
            val albumState = albumRepository.refreshAlbums()
            if (albumState.isError) {
                isError.value = true
                isLoading.value = false
            } else {
                albums.value = albumState.albums ?: emptyList()
                isError.value = false
                isLoading.value = false
            }
        }
    }
}
