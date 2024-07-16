package com.eselman.albumsapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    val name: String
): Parcelable
