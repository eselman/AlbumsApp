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
class AlbumDetailsViewModel @Inject constructor(
    private val albumRepository: AlbumRepository
): ViewModel() {
    val album = MutableLiveData<AlbumRealm?>()

    fun getAlbum(albumId: String) {
        viewModelScope.launch {
            album.value = albumRepository.getAlbumById(albumId)
        }
    }
}
