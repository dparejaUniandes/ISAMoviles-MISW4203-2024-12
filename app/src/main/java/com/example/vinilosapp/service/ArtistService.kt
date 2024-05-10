package com.example.vinilosapp.service

import com.example.vinilosapp.models.Artist
import com.example.vinilosapp.models.Comment
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ArtistService {
    @GET("musicians")
    fun getArtists():
            Call<List<Artist>>
    @GET("musicians/{artistId}")
    suspend fun getArtist(@Path("artistId") artistId : Int): Artist
}