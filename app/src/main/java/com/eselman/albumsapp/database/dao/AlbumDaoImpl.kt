package com.eselman.albumsapp.database.dao

import com.eselman.albumsapp.database.entities.AlbumRealm
import io.realm.kotlin.Realm
import javax.inject.Inject

class AlbumDaoImpl @Inject constructor(
    private val realm: Realm,
): AlbumDao {
    override suspend fun insertAlbums(albums: List<AlbumRealm>) {
        albums.forEach { album ->
            realm.write {
                copyToRealm(album)
            }
        }
    }

    override suspend fun getAllAlbums(): List<AlbumRealm> {
        return realm.query(AlbumRealm::class).find()
    }

    override suspend fun getAlbumById(id: String): AlbumRealm? {
        return realm.query(AlbumRealm::class, "id == $0", id).first().find()
    }

    override suspend fun deleteAllAlbums() {
        realm.write {
            val albums = this.query(AlbumRealm::class).find()
            delete(albums)
        }
    }
}