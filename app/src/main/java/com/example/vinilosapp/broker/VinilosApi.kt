package com.example.vinilosapp.broker

import com.example.vinilosapp.service.AlbumService
import com.example.vinilosapp.service.ArtistService
import com.example.vinilosapp.service.CollectorService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://35.193.69.44:3000/"

private val vinilosRetrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()
object VinilosApi {
    val albumService : AlbumService by lazy {
        vinilosRetrofit.create(AlbumService::class.java) }

    val artistService : ArtistService by lazy {
        vinilosRetrofit.create(ArtistService::class.java) }

    val collectorService : CollectorService by lazy {
        vinilosRetrofit.create(CollectorService::class.java) }
}