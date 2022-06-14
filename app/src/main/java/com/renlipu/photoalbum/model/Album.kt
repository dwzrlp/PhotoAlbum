package com.renlipu.photoalbum.model

/**
 * @program: Photo Album
 *
 * @description:
 *
 * @author: Lipu
 *
 * @create: 2022-06-10 14:47
 **/

data class Album(
    val id: Int,
    val title: String,
    val userId: Int,
)

data class Photo(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)

data class AlbumWithUserName(
    val id: Int,
    val title: String,
    val userId: Int,
    val userName: String
)



