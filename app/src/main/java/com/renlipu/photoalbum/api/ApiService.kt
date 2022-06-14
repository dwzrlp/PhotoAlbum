package com.renlipu.photoalbum.api

import com.renlipu.photoalbum.model.Album
import com.renlipu.photoalbum.model.Photo
import com.renlipu.photoalbum.model.User
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @program: Photo Album
 *
 * @description: A retrofit service to fetch album.
 *
 * @author: Lipu
 *
 * @create: 2022-06-10 14:47
 **/
interface ApiService {

    @GET("albums")
    suspend fun getAlbums(): List<Album>

    @GET("albums/{id}/photos")
    suspend fun getPhotos(@Path("id") id: Int): List<Photo>

    @GET("users")
    suspend fun getUsers(): List<User>
}