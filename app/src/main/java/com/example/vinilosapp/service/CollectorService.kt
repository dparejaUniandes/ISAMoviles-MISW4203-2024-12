package com.example.vinilosapp.service

import com.example.vinilosapp.models.Collector
import retrofit2.Call
import retrofit2.http.GET

interface CollectorService {
    @GET("collectors")
    fun getCollectors():
            Call<List<Collector>>
}