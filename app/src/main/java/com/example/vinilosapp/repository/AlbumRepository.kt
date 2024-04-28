package com.example.vinilosapp.repository

import android.app.Application
import com.example.vinilosapp.broker.AlbumBroker
import com.example.vinilosapp.models.Album

class AlbumRepository(val application: Application){
    fun refreshData(callback: (List<Album>)->Unit, onFailure: (String)->Unit) {
        AlbumBroker.getInstance(application).getAlbums({
            callback(it)
        },
            onFailure
        )
    }

    fun refreshData(albumId: (Int), callback: (Album)->Unit, onFailure: (String)->Unit) {
        AlbumBroker.getInstance(application).getAlbum(
            albumId,
            {
            callback(it)
            },
            onFailure
        )
    }
}