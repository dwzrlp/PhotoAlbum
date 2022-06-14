package com.renlipu.photoalbum.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.renlipu.photoalbum.adapter.PhotoAdapter
import com.renlipu.photoalbum.databinding.FragmentPhotoBinding
import com.renlipu.photoalbum.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Show a grid of the photos on gallery screen.
 */
@AndroidEntryPoint
class PhotoFragment : Fragment() {

    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel by activityViewModels<MainViewModel>()

    /**
     * RecyclerView Adapter for converting a list of photo to grid.
     */
    private var photoAdapter: PhotoAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)
        mainViewModel.getPhotoFromRepository(mainViewModel.albumId)
        photoAdapter = PhotoAdapter()

        binding.rvPhoto.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
            adapter = photoAdapter
        }

        // Observer for the network error.
        mainViewModel.eventNetworkError.observe(viewLifecycleOwner) { isNetworkError ->
            if (isNetworkError) onNetworkError()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.photoList.observe(viewLifecycleOwner) { photolist ->
            val newPhotoList = photolist.filter {
                it.id == mainViewModel.albumId
            }
            photoAdapter?.submitList(newPhotoList)
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