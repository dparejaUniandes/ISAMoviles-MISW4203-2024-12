package com.example.vinilosapp.broker

import android.content.Context
import com.example.vinilosapp.models.Collector
import java.lang.Exception

class CollectorBroker(context: Context) {
    companion object{
        var instance: CollectorBroker? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CollectorBroker(context).also {
                    instance = it
                }
            }
    }

    suspend fun getCollectors() : Result<List<Collector>> {
        try {
            var collectors = VinilosApi.collectorService.getCollectors()
            return Result.success(collectors)
        } catch (e : Exception) {
            return Result.failure(e)
        }
    }

}