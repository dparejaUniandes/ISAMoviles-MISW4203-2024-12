package com.example.vinilosapp.service

import com.example.vinilosapp.models.Track
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TrackService {
    @GET("albums/1/tracks")
    suspend fun getTracks(): List<Track>
    @GET("albums/1/tracks/{trackId}")
    suspend fun getTrack(@Path("trackId") trackId : Int): Track
    @FormUrlEncoded
    @POST("albums/{albumId}/tracks")
    suspend fun associateTrack(@Path("albumId") albumIdId : Int,
                               @Field("name") name: String,
                               @Field("duration") duration: String): Track
}