package com.eselman.albumsapp.utils

import android.os.Build
import android.widget.ImageView
import coil.load
import com.eselman.albumsapp.database.entities.AlbumRealm
import com.eselman.albumsapp.model.Album
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Locale

fun Album.toAlbumRealm(copyright: String): AlbumRealm {
    val genre = genres?.let {
        if (it.size > 1) {
            it.firstOrNull { genre -> genre.name != "Music" }
        } else {
            it[0]
        }
    }

    return AlbumRealm(
        id = this.id,
        name = this.name,
        artistName = this.artistName,
        artworkUrl100 = this.artworkUrl100,
        releaseDate = this.releaseDate,
        genre = genre?.name ?: "",
        copyright = copyright,
        url = this.url ?: ""
    )
}

fun ImageView.loadImage(uri: String?, defaultResource: Int) {
    this.load(uri) {
        crossfade(true)
        placeholder(defaultResource)
    }
}

fun String.extractYearFromDate(): Int =
    try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val date = LocalDate.parse(this)
            date.year
        } else {
            val simpleDateFormat = SimpleDateFormat("yyyy-mm-dd", Locale.US)
            val date = simpleDateFormat.parse(this)
            date?.let {
                val calendar = Calendar.getInstance()
                calendar.time = date
                calendar.get(Calendar.YEAR)
            } ?: 0
        }
    } catch (e: Exception) {
        0
    }

