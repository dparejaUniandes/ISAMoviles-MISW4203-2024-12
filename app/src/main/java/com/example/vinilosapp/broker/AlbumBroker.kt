package com.example.vinilosapp.broker

import android.content.Context
import android.util.Log
import com.example.vinilosapp.models.Album
import com.example.vinilosapp.models.Comment
import retrofit2.Call
import retrofit2.Callback
import java.lang.Exception

class AlbumBroker constructor(context: Context) {
    companion object{
        var instance: AlbumBroker? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: AlbumBroker(context).also {
                    instance = it
                }
            }
    }

    suspend fun getAlbums() : Result<List<Album>> {
        try {
            var albums = VinilosApi.albumService.getAlbums()
            return Result.success(albums)
        } catch (e : Exception) {
            return Result.failure(e)
        }
    }

    suspend fun getAlbum(albumId : (Int)) : Result<Album>  {
        try {
            var album = VinilosApi.albumService.getAlbum(albumId)
            return Result.success(album)
        } catch (e : Exception) {
            return Result.failure(e)
        }
    }
}