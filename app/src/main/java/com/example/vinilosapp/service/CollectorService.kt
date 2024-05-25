package com.example.vinilosapp.service

import com.example.vinilosapp.models.Collector
import retrofit2.http.GET
import retrofit2.http.Path

interface CollectorService {
    @GET("collectors")
    suspend fun getCollectors(): List<Collector>

    @GET("collectors/{collectorId}")
    suspend fun getCollector(@Path("collectorId") collectorId : Int): Collector

}