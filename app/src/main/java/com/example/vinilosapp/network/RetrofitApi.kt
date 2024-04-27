package com.example.vinilosapp.network

import com.example.vinilosapp.models.Album
import com.example.vinilosapp.models.Collector
import com.example.vinilosapp.models.Comment
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

private const val BASE_URL = "http://10.0.2.2:3000/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface RetrofitApiService {
    @GET("collectors")
    fun getCollectors():
            Call<List<Collector>>

    @GET("albums")
    fun getAlbums():
            Call<List<Album>>

    @GET("albums/{albumId}/comments")
    fun getComments(@Path("albumId") ambumId : Int):
            Call<List<Comment>>


}

object RetrofitApi {
    val retrofitService : RetrofitApiService by lazy {
        retrofit.create(RetrofitApiService::class.java) }
}