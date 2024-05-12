package com.example.vinilosapp.broker

import android.content.Context
import com.example.vinilosapp.models.Artist
<<<<<<< HEAD
=======
import retrofit2.Call
import retrofit2.Callback
>>>>>>> ce07bc8312cb315ef38376dd194d7ef1b3a0e35b
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
<<<<<<< HEAD
        return try {
            val artists = VinilosApi.artistService.getArtists()
            Result.success(artists)
        } catch (e : Exception) {
            Result.failure(e)
=======
        try {
            val artists = VinilosApi.artistService.getArtists()
            return Result.success(artists)
        } catch (e : Exception) {
            return Result.failure(e)
>>>>>>> ce07bc8312cb315ef38376dd194d7ef1b3a0e35b
        }
    }

    suspend fun getArtist(artistId : (Int)) : Result<Artist> {
<<<<<<< HEAD
        return try {
            val artist = VinilosApi.artistService.getArtist(artistId)
            Result.success(artist)
        } catch (e : Exception) {
            Result.failure(e)
=======
        try {
            val artist = VinilosApi.artistService.getArtist(artistId)
            return Result.success(artist)
        } catch (e : Exception) {
            return Result.failure(e)
>>>>>>> ce07bc8312cb315ef38376dd194d7ef1b3a0e35b
        }
    }
}