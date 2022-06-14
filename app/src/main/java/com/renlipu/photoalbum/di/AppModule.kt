package com.renlipu.photoalbum.di

import android.app.Application
import com.renlipu.photoalbum.BuildConfig
import com.renlipu.photoalbum.api.ApiService
import com.renlipu.photoalbum.database.AlbumDao
import com.renlipu.photoalbum.database.AlbumDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @program: Photo Album
 *
 * @description: Modules
 *
 * @author: Lipu
 *
 * @create: 2022-06-10 14:59
 **/

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply { level = Level.BODY }

        return OkHttpClient().newBuilder()
            .addInterceptor(logger)
            .connectTimeout(15, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideDb(app: Application): AlbumDatabase {
        return AlbumDatabase.getInstance(app)
    }

    @Singleton
    @Provides
    fun provideAlbumDao(db: AlbumDatabase): AlbumDao {
        return db.albumDao
    }

}