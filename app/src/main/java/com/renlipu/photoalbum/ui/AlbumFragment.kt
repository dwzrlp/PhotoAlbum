package com.renlipu.photoalbum.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.renlipu.photoalbum.R
import com.renlipu.photoalbum.adapter.AlbumAdapter
import com.renlipu.photoalbum.databinding.FragmentAlbumBinding
import com.renlipu.photoalbum.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * Show a list of album on screen.
 * The left is the album name, and the right is the album author's name.
 * The list is sorted in ascending order by album name.
 */
@AndroidEntryPoint
class AlbumFragment : Fragment() {

    private var _binding: FragmentAlbumBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel by activityViewModels<MainViewModel>()

    /**
     * RecyclerView Adapter for converting a list of album to cards.
     */
    private var albumAdapter: AlbumAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumBinding.inflate(inflater, container, false)

        albumAdapter = AlbumAdapter {
            mainViewModel.albumId = it
            findNavController().navigate(R.id.action_albumFragment_to_photoFragment)
        }

        binding.rvAlbum.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = albumAdapter
        }

        // Observer for the network error.
        mainViewModel.eventNetworkError.observe(viewLifecycleOwner) { isNetworkError ->
            if (isNetworkError) onNetworkError()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.albumList.observe(viewLifecycleOwner) {
            albumAdapter?.submitList(it.sortedBy { album -> album.title })
        }
    }

    /**
     * Method for displaying a Toast error message for network errors.
     */
    private fun onNetworkError() {
        if (!mainViewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            mainViewModel.onNetworkErrorShown()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}