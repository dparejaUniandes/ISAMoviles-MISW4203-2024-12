package com.movil.vinilosapp.models.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VinilosService(private val urlService: String) {

    companion object Factory {
        fun create(urlService: String): VinilosService {
            return VinilosService(urlService)
        }
    } // End Factory

    private val api: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(urlService)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getAlbum(): IAlbum {
        return api.create(IAlbum::class.java)
    } // End getAlbum

} // End class