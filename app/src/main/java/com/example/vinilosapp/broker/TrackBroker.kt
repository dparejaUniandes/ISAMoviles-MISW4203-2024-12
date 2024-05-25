package com.example.vinilosapp.broker

import android.content.Context
import com.example.vinilosapp.models.Track
import java.lang.Exception

class TrackBroker(context: Context) {
    companion object{
        private var instance: TrackBroker? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: TrackBroker(context).also {
                    instance = it
                }
            }
    }

    suspend fun getTracks() : Result<List<Track>> {
        return try {
            val tracks = VinilosApi.trackService.getTracks()
            Result.success(tracks)
        } catch (e : Exception) {
            Result.failure(e)
        }
    }

    suspend fun getTrack(trackId : (Int)) : Result<Track>  {
        return try {
            val track = VinilosApi.trackService.getTrack(trackId)
            Result.success(track)
        } catch (e : Exception) {
            Result.failure(e)
        }
    }

    suspend fun associateTrack(albumId: (Int), name: (String), duration: (String)) : Result<Track>  {
        return try {
            val track = VinilosApi.trackService.associateTrack(albumId,
                name,
                duration)
            Result.success(track)
        } catch (e : Exception) {
            Result.failure(e)
        }
    }
}