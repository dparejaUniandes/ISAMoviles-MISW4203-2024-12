package com.example.vinilosapp.broker

import android.content.Context
import com.example.vinilosapp.models.Artist
import java.lang.Exception

class ArtistBroker(context: Context) {
    companion object{
        private var instance: ArtistBroker? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: ArtistBroker(context).also {
                    instance = it
                }
            }
    }

    suspend fun getArtists() : Result<List<Artist>> {
        return try {
            val artists = VinilosApi.artistService.getArtists()
            Result.success(artists)
        } catch (e : Exception) {
            Result.failure(e)
        }
    }

    suspend fun getArtist(artistId : (Int)) : Result<Artist> {
        return try {
            val artist = VinilosApi.artistService.getArtist(artistId)
            Result.success(artist)
        } catch (e : Exception) {
            Result.failure(e)
        }
    }
}