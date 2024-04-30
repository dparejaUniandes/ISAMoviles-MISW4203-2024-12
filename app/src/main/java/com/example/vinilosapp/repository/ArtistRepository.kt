package com.example.vinilosapp.repository

import android.app.Application
import com.example.vinilosapp.broker.ArtistBroker
import com.example.vinilosapp.models.Artist

class ArtistRepository(val application: Application){
    fun refreshData(callback: (List<Artist>)->Unit, onFailure: (String)->Unit) {
        ArtistBroker.getInstance(application).getArtists({
            callback(it)
        },
            onFailure
        )
    }

    fun refreshData(artistId: (Int), callback: (Artist)->Unit, onFailure: (String)->Unit) {
        ArtistBroker.getInstance(application).getArtist(
            artistId,
            {
            callback(it)
            },
            onFailure
        )
    }
}