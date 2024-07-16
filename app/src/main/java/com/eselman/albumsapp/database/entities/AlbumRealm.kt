package com.eselman.albumsapp.database.entities

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class AlbumRealm() : RealmObject {
    @PrimaryKey
    var id: String = ""
    var name: String = ""
    var artistName: String = ""
    var artworkUrl100: String = ""
    var releaseDate: String = ""
    var genre: String = ""
    var copyright: String = ""
    var url: String = ""

    constructor(
        id: String,
        name: String,
        artistName: String,
        artworkUrl100: String,
        releaseDate: String,
        genre: String,
        copyright: String,
        url: String
    ): this(){
        this.id = id
        this.name = name
        this.artistName = artistName
        this.artworkUrl100 = artworkUrl100
        this.releaseDate = releaseDate
        this.genre = genre
        this.copyright = copyright
        this.url = url
    }
}
