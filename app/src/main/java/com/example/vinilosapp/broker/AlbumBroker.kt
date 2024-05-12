package com.example.vinilosapp.broker

import android.content.Context
import com.example.vinilosapp.models.Album
<<<<<<< HEAD
=======
import com.example.vinilosapp.models.Comment
import retrofit2.Call
import retrofit2.Callback
>>>>>>> ce07bc8312cb315ef38376dd194d7ef1b3a0e35b
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
<<<<<<< HEAD
        return try {
            val albums = VinilosApi.albumService.getAlbums()
            Result.success(albums)
        } catch (e : Exception) {
            Result.failure(e)
=======
        try {
            var albums = VinilosApi.albumService.getAlbums()
            return Result.success(albums)
        } catch (e : Exception) {
            return Result.failure(e)
>>>>>>> ce07bc8312cb315ef38376dd194d7ef1b3a0e35b
        }
    }

    suspend fun getAlbum(albumId : (Int)) : Result<Album>  {
<<<<<<< HEAD
        return try {
            val album = VinilosApi.albumService.getAlbum(albumId)
            Result.success(album)
        } catch (e : Exception) {
            Result.failure(e)
=======
        try {
            var album = VinilosApi.albumService.getAlbum(albumId)
            return Result.success(album)
        } catch (e : Exception) {
            return Result.failure(e)
>>>>>>> ce07bc8312cb315ef38376dd194d7ef1b3a0e35b
        }
    }
}