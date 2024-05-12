package com.example.vinilosapp.broker

import android.content.Context
import com.example.vinilosapp.models.Album
import java.lang.Exception

class AlbumBroker(context: Context) {
    companion object{
        private var instance: AlbumBroker? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: AlbumBroker(context).also {
                    instance = it
                }
            }
    }

    suspend fun getAlbums() : Result<List<Album>> {
        return try {
            val albums = VinilosApi.albumService.getAlbums()
            Result.success(albums)
        } catch (e : Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAlbum(albumId : (Int)) : Result<Album>  {
        return try {
            val album = VinilosApi.albumService.getAlbum(albumId)
            Result.success(album)
        } catch (e : Exception) {
            Result.failure(e)
        }
    }
}