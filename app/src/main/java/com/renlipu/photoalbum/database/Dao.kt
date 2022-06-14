package com.renlipu.photoalbum.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * @program: Photo Album
 *
 * @description:
 *
 * @author: Lipu
 *
 * @create: 2022-06-10 21:12
 **/
@Dao
interface AlbumDao {

    @Query("select * from UserTable inner join AlbumTable on UserTable.id = AlbumTable.userId")
    fun getAlbums(): LiveData<List<AlbumWithNameTable>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAlbums(albums: List<AlbumTable>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllUsers(albums: List<UserTable>)

    @Query("select * from PhotoTable")
    fun getPhotos(): LiveData<List<PhotoTable>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPhotos(albums: List<PhotoTable>)

}