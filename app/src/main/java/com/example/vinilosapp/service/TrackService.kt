package com.example.vinilosapp.service

import com.example.vinilosapp.models.Track
import retrofit2.http.GET
import retrofit2.http.Path

interface TrackService {
    @GET("albums/1/tracks")
    suspend fun getTracks(): List<Track>
    @GET("albums/1/tracks/{trackId}")
    suspend fun getTrack(@Path("trackId") trackId : Int): Track
}