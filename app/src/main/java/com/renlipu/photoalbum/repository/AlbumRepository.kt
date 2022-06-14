package com.renlipu.photoalbum.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.renlipu.photoalbum.api.ApiService
import com.renlipu.photoalbum.database.AlbumDao
import com.renlipu.photoalbum.database.AlbumTable
import com.renlipu.photoalbum.database.UserTable
import com.renlipu.photoalbum.model.AlbumWithUserName
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
class AlbumRepository @Inject constructor(
    private val albumDao: AlbumDao,
    private val apiService: ApiService
) : Repository {
    val albums: LiveData<List<AlbumWithUserName>> =
        Transformations.map(albumDao.getAlbums()) { albumList ->
            albumList.map {
                AlbumWithUserName(
                    it.id, it.title, it.userId, it.name
                )
            }
        }

    suspend fun getAlbums() {
        withContext(Dispatchers.IO) {
            val albumList = apiService.getAlbums()
            albumDao.insertAllAlbums(albumList.map {
                AlbumTable(it.id, it.title, it.userId)
            })

            val userList = apiService.getUsers()
            albumDao.insertAllUsers(userList.map {
                UserTable(it.id, it.name)
            })
        }
    }
}