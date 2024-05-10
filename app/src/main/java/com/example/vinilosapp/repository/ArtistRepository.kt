package com.example.vinilosapp.repository

import android.app.Application
import com.example.vinilosapp.broker.ArtistBroker
import com.example.vinilosapp.models.Artist

class ArtistRepository(val application: Application){
    suspend fun refreshData() : Result<List<Artist>>{
        return ArtistBroker.getInstance(application).getArtists()
    }

    suspend fun refreshData(artistId: (Int)) : Result<Artist> {
        return ArtistBroker.getInstance(application).getArtist(artistId)
    }
}