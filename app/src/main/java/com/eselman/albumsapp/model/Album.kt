package com.eselman.albumsapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Album(
    val id: String,
    val name: String,
    val artistName: String,
    val artworkUrl100: String,
    val releaseDate: String,
    val genres: List<Genre>?,
    val url: String?
): Parcelable
