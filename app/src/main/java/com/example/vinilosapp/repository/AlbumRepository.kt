package com.example.vinilosapp.repository

import android.app.Application
import com.example.vinilosapp.broker.AlbumBroker
import com.example.vinilosapp.models.Album

class AlbumRepository(val application: Application){
    suspend fun refreshData() : Result<List<Album>> {
        return AlbumBroker.getInstance(application).getAlbums()
    }

    suspend fun refreshData(albumId: (Int)) : Result<Album>{
        return AlbumBroker.getInstance(application).getAlbum(albumId)
    }
}