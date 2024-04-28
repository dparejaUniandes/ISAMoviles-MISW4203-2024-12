package com.example.vinilosapp.service

import com.example.vinilosapp.models.Album
import com.example.vinilosapp.models.Comment
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumService {
    @GET("albums")
    fun getAlbums():
            Call<List<Album>>
    @GET("albums/{albumId}")
    fun getAlbum(@Path("albumId") albumId : Int):
            Call<Album>
}