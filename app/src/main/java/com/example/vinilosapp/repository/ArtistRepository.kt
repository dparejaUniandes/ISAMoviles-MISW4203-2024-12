package com.example.vinilosapp.repository

import android.app.Application
import android.util.Log
import com.example.vinilosapp.broker.ArtistBroker
import com.example.vinilosapp.broker.CacheManager
import com.example.vinilosapp.models.Artist

class ArtistRepository(val application: Application){
    suspend fun refreshData() : Result<List<Artist>>{
        return ArtistBroker.getInstance(application).getArtists()
    }

    suspend fun refreshData(artistId: (Int)) : Result<Artist> {
<<<<<<< HEAD
        val cacheManager = CacheManager.getInstance(application.applicationContext)
        var artistDetailCache = cacheManager.getArtistDetail(artistId)
        if(artistDetailCache.artistId < 0) {
            Log.d("Cache decision", "get from network")
            val artistDetailResponse = ArtistBroker.getInstance(application).getArtist(artistId)
=======
        var cacheManager = CacheManager.getInstance(application.applicationContext)
        var artistDetailCache = cacheManager.getArtistDetail(artistId)
        if(artistDetailCache.artistId < 0) {
            Log.d("Cache decision", "get from network")
            var artistDetailResponse = ArtistBroker.getInstance(application).getArtist(artistId)
>>>>>>> ce07bc8312cb315ef38376dd194d7ef1b3a0e35b
            artistDetailResponse.onSuccess{
                artistDetailCache = it
                cacheManager.addArtistDetail(artistId, it)
            }
            return artistDetailResponse
        }
        return Result.success(artistDetailCache)
    }
}