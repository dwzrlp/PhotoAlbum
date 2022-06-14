package com.renlipu.photoalbum.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * @program: Photo Album
 *
 * @description: DatabaseVideo represents a Album entity, a User entity, a Photo entity
 * and a album with user name entity in the database.
 *
 * @author: Lipu
 *
 * @create: 2022-06-10 19:06
 **/


@Database(
    entities = [AlbumTable::class, PhotoTable::class, UserTable::class, AlbumWithNameTable::class],
    version = 1,
    exportSchema = false
)
abstract class AlbumDatabase : RoomDatabase() {

    abstract val albumDao: AlbumDao

    companion object {

        @Volatile
        private var INSTANCE: AlbumDatabase? = null

        fun getInstance(app: Application): AlbumDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(app).also { INSTANCE = it }
        }

        private fun buildDatabase(app: Application) =
            Room.databaseBuilder(app, AlbumDatabase::class.java, "albums")
                .allowMainThreadQueries()
                .build()
    }
}

