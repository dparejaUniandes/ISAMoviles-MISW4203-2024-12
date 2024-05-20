package com.example.vinilosapp.service

import com.example.vinilosapp.models.Album
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AlbumService {
    @GET("albums")
    suspend fun getAlbums(): List<Album>
    @GET("albums/{albumId}")
    suspend fun getAlbum(@Path("albumId") albumId : Int): Album

    @FormUrlEncoded
    @POST("albums")
    suspend fun postAlbum(@Field("name") name: String,
                          @Field("cover") cover: String,
                          @Field("releaseDate") releaseDate: String,
                          @Field("description") description: String,
                          @Field("genre") genre: String,
                          @Field("recordLabel") recordLabel: String,): Album
}