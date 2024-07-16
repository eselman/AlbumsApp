package com.eselman.albumsapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.eselman.albumsapp.R
import com.eselman.albumsapp.database.entities.AlbumRealm
import com.eselman.albumsapp.utils.loadImage

class AlbumsAdapter(private val albumsList: MutableList<AlbumRealm>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        AlbumsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.album_card, parent, false))


    override fun getItemCount(): Int = albumsList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val album = albumsList[position]
        (holder as AlbumsViewHolder).bind(album)
    }

    fun updateAlbumsList(newAlbums: List<AlbumRealm>) {
        albumsList.clear()
        albumsList.addAll(newAlbums)
        notifyDataSetChanged()
    }

    class AlbumsViewHolder(private val albumView: View): RecyclerView.ViewHolder(albumView) {
        private val albumThumbnailView = albumView.findViewById<ImageView>(R.id.album_thumbnail)
        private val albumNameTextView = albumView.findViewById<TextView>(R.id.album_name)
        private val albumArtistTextView = albumView.findViewById<TextView>(R.id.album_artist)
        private val albumClickableView = albumView.findViewById<View>(R.id.clickable_overlay)

        fun bind(album: AlbumRealm) {
            album.apply {
                albumThumbnailView.loadImage(album.artworkUrl100, R.drawable.ic_launcher_foreground)
                albumNameTextView.text = name
                albumArtistTextView.text = artistName
                albumClickableView.setOnClickListener {
                    val albumId = bundleOf("albumId" to album.id)
                    Navigation.findNavController(albumView)
                        .navigate(R.id.action_albumsListFragment_to_albumDetailsFragment, albumId)
                }
            }
        }
    }
}