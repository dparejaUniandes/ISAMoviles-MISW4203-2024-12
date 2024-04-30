package com.example.vinilosapp.service

import com.example.vinilosapp.models.Artist
import com.example.vinilosapp.models.Comment
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ArtistService {
    @GET("artists")
    fun getArtists():
            Call<List<Artist>>
    @GET("artists/{artistId}")
    fun getArtist(@Path("artistId") artistId : Int):
            Call<Artist>
}