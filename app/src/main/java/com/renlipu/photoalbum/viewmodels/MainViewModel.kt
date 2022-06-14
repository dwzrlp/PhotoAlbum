package com.renlipu.photoalbum.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.renlipu.photoalbum.repository.AlbumRepository
import com.renlipu.photoalbum.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

/**
 * @program: Photo Album
 *
 * @description:
 *
 * @author: Lipu
 *
 * @create: 2022-06-10 15:44
 **/
@HiltViewModel
class MainViewModel @Inject constructor(
    private val albumRepository: AlbumRepository,
    private val photoRepository: PhotoRepository,
) : ViewModel() {

    var albumId = 1

    private var _eventNetworkError = MutableLiveData(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    val albumList = albumRepository.albums

    init {
        getAlbumFromRepository()
    }

    private fun getAlbumFromRepository() {
        viewModelScope.launch {
            try {
                albumRepository.getAlbums()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            } catch (networkError: IOException) {
                // Show a Toast error message and hide the progress bar.
                if (albumList.value.isNullOrEmpty())
                    _eventNetworkError.value = true
            }
        }
    }

    val photoList = photoRepository.photos

    fun getPhotoFromRepository(id: Int) {
        viewModelScope.launch {
            try {
                photoRepository.getPhotos(id)
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            } catch (networkError: IOException) {
                // Show a Toast error message and hide the progress bar.
                if (photoList.value.isNullOrEmpty())
                    _eventNetworkError.value = true
            }
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

}