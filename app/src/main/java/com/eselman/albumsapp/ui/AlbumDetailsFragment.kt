package com.eselman.albumsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.eselman.albumsapp.R
import com.eselman.albumsapp.utils.extractYearFromDate
import com.eselman.albumsapp.utils.loadImage
import com.eselman.albumsapp.viewmodel.AlbumDetailsViewModel

class AlbumDetailsFragment : Fragment() {
    private val albumDetailsViewModel: AlbumDetailsViewModel by activityViewModels()

    private lateinit var albumDetailsArtImage: ImageView
    private lateinit var albumDetailsName: TextView
    private lateinit var albumDetailsArtistName: TextView
    private lateinit var albumDetailsGenre: TextView
    private lateinit var albumCopyright: TextView
    private lateinit var goToITunesBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_album_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val albumId = arguments?.getString("albumId") ?: ""

        albumDetailsArtImage = view.findViewById(R.id.album_details_art_image)
        albumDetailsName = view.findViewById(R.id.album_details_name)
        albumDetailsArtistName = view.findViewById(R.id.album_details_artist_name)
        albumDetailsGenre = view.findViewById(R.id.album_details_genre_year)
        albumCopyright = view.findViewById(R.id.album_copyright)
        goToITunesBtn = view.findViewById(R.id.go_to_itunes_btn)

        albumDetailsViewModel.getAlbum(albumId)
        observeViewModel()
    }

    private fun observeViewModel() {
        albumDetailsViewModel.album.observe(viewLifecycleOwner) { album ->
            album?.apply {
                (requireActivity() as AppCompatActivity).supportActionBar?.title = name
                albumDetailsArtImage.loadImage(artworkUrl100, R.mipmap.ic_launcher)
                albumDetailsName.text = name
                albumDetailsArtistName.text = artistName
                albumDetailsGenre.text = getString(R.string.genre_year, genre, releaseDate.extractYearFromDate())
                albumCopyright.text = copyright
                val itunesUri = bundleOf("itunesUri" to url)
                goToITunesBtn.setOnClickListener {
                    findNavController().navigate(R.id.action_albumDetailsFragment_to_albumWebViewFragment, itunesUri)
                }
            }
        }
    }
}