package com.renlipu.photoalbum.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @program: Photo Album
 *
 * @description: DatabaseVideo represents a Album entity, a User entity, a Photo entity
 * and a album with user name entity in the database.
 *
 * @author: Lipu
 *
 * @create: 2022-06-10 19:03
 **/

@Entity
data class AlbumTable constructor(
    @PrimaryKey
    val id: Int,
    val title: String,
    val userId: Int
)

@Entity
data class AlbumWithNameTable constructor(
    @PrimaryKey
    val id: Int,
    val title: String,
    val userId: Int,
    val name: String
)

@Entity
data class PhotoTable constructor(
    @PrimaryKey
    val id: Int,
    val albumId: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)

@Entity
data class UserTable constructor(
    @PrimaryKey
    val id: Int,
    val name: String,
)
