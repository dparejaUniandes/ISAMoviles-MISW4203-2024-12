package com.example.vinilosapp.service

import com.example.vinilosapp.models.Album
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumService {
    @GET("albums")
    suspend fun getAlbums(): List<Album>
    @GET("albums/{albumId}")
    suspend fun getAlbum(@Path("albumId") albumId : Int): Album
}