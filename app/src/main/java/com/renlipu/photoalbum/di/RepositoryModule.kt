package com.renlipu.photoalbum.di

import com.renlipu.photoalbum.api.ApiService
import com.renlipu.photoalbum.database.AlbumDao
import com.renlipu.photoalbum.repository.AlbumRepository
import com.renlipu.photoalbum.repository.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideAlbumRepository(
        albumDao: AlbumDao,
        apiService: ApiService
    ): AlbumRepository {
        return AlbumRepository(albumDao, apiService)
    }

    @Provides
    @ViewModelScoped
    fun providePhotoRepository(
        albumDao: AlbumDao,
        apiService: ApiService
    ): PhotoRepository {
        return PhotoRepository(albumDao, apiService)
    }

}
