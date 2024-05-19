package com.example.vinilosapp.repository

import android.app.Application
import android.util.Log
import com.example.vinilosapp.broker.AlbumBroker
import com.example.vinilosapp.models.Album

class AlbumRepository(val application: Application){
    suspend fun refreshData() : Result<List<Album>> {
        return AlbumBroker.getInstance(application).getAlbums()
    }

    suspend fun refreshData(albumId: (Int)) : Result<Album>{
        return AlbumBroker.getInstance(application).getAlbum(albumId)
    }

    suspend fun refreshData(body: Map<String, String>) : Result<Album>{
        Log.d("Body in repository", body.toString())
        return AlbumBroker.getInstance(application).postAlbum(body)
    }
}