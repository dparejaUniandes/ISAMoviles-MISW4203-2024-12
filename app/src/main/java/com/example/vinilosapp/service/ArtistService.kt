package com.example.vinilosapp.service

import com.example.vinilosapp.models.Artist
import retrofit2.http.GET
import retrofit2.http.Path

interface ArtistService {
    @GET("musicians")
    suspend fun getArtists(): List<Artist>

    @GET("musicians/{artistId}")
    suspend fun getArtist(@Path("artistId") artistId : Int): Artist
}