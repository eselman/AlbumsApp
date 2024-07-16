package com.eselman.albumsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.eselman.albumsapp.R
import com.eselman.albumsapp.ui.adapters.AlbumsAdapter
import com.eselman.albumsapp.viewmodel.AlbumsListViewModel

class AlbumsListFragment : Fragment() {
    private val albumsListViewModel: AlbumsListViewModel by activityViewModels()

    private lateinit var albumsAdapter: AlbumsAdapter

    private lateinit var albumsRecyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var loadingView: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_albums_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        albumsListViewModel.getAlbums()

        albumsRecyclerView = view.findViewById(R.id.albums_recycler)

        albumsRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            albumsAdapter = AlbumsAdapter(mutableListOf())
            adapter = albumsAdapter
        }

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = true
            albumsListViewModel.refreshAlbums()
        }

        loadingView = view.findViewById(R.id.status_loading_wheel)

        observeViewModel()
    }

    private fun observeViewModel() {
        albumsListViewModel.albums.observe(viewLifecycleOwner) { albums ->
            albums?.let {
                albumsRecyclerView.visibility = View.VISIBLE
                albumsAdapter.updateAlbumsList(albums)
            }
            if (swipeRefreshLayout.isRefreshing) {
                swipeRefreshLayout.isRefreshing = false
            }
        }

        albumsListViewModel.isError.observe(viewLifecycleOwner) { isError ->
            if (isError) {
                findNavController().navigate(R.id.action_albumsListFragment_to_albumErrorFragment)
            }
        }

        albumsListViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            isLoading?.let {
                loadingView.visibility = if (it && !swipeRefreshLayout.isRefreshing) View.VISIBLE else View.GONE
                if (it) {
                    albumsRecyclerView.visibility = View.GONE
                }
            }
        }
    }
}
