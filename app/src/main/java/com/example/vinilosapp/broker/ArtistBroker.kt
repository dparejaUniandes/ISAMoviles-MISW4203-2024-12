package com.example.vinilosapp.broker

import android.content.Context
import android.util.Log
import com.example.vinilosapp.models.Artist
import retrofit2.Call
import retrofit2.Callback
import java.lang.Exception

class ArtistBroker constructor(context: Context) {
    companion object{
        var instance: ArtistBroker? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: ArtistBroker(context).also {
                    instance = it
                }
            }
    }

    suspend fun getArtists() : Result<List<Artist>> {
        try {
            val artists = VinilosApi.artistService.getArtists()
            return Result.success(artists)
        } catch (e : Exception) {
            return Result.failure(e)
        }
    }

    suspend fun getArtist(artistId : (Int)) : Result<Artist> {
        try {
            val artist = VinilosApi.artistService.getArtist(artistId)
            return Result.success(artist)
        } catch (e : Exception) {
            return Result.failure(e)
        }
    }
}