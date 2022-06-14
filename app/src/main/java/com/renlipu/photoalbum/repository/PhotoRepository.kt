package com.renlipu.photoalbum.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.renlipu.photoalbum.api.ApiService
import com.renlipu.photoalbum.database.AlbumDao
import com.renlipu.photoalbum.database.AlbumTable
import com.renlipu.photoalbum.database.PhotoTable
import com.renlipu.photoalbum.database.UserTable
import com.renlipu.photoalbum.model.AlbumWithUserName
import com.renlipu.photoalbum.model.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @program: Photo Album
 *
 * @description: Repository for fetching album from the network and storing them on disk
 *
 * @author: Lipu
 *
 * @create: 2022-06-10 19:18
 **/

@Singleton
class PhotoRepository @Inject constructor(
    private val albumDao: AlbumDao,
    private val apiService: ApiService
) : Repository {

    val photos: LiveData<List<Photo>> = Transformations.map(albumDao.getPhotos()) { photoList ->
        photoList.map {
            Photo(it.id, it.albumId, it.thumbnailUrl, it.title, it.url)
        }
    }

    suspend fun getPhotos(id: Int) {
        withContext(Dispatchers.IO) {
            val photoList = apiService.getPhotos(id)
            albumDao.insertAllPhotos(photoList.map {
                PhotoTable(it.id, it.albumId, it.thumbnailUrl, it.title, it.url)
            }
            )
        }

    }
}