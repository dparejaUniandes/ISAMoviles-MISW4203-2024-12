package com.movil.vinilosapp.models.service

import com.movil.vinilosapp.models.dto.AlbumDto

import retrofit2.Call
import retrofit2.http.GET

interface IAlbum {

    @GET("albums")
    fun getAlbumList(): Call<ArrayList<AlbumDto>>;

} // End interface