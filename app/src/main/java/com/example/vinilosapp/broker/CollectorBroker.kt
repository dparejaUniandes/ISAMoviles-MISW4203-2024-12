package com.example.vinilosapp.broker

import android.content.Context
import com.example.vinilosapp.models.Collector
import java.lang.Exception

class CollectorBroker(context: Context) {
    companion object{
        private var instance: CollectorBroker? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CollectorBroker(context).also {
                    instance = it
                }
            }
    }

    suspend fun getCollectors() : Result<List<Collector>> {
        return try {
            val collectors = VinilosApi.collectorService.getCollectors()
            Result.success(collectors)
        } catch (e : Exception) {
            Result.failure(e)
        }
    }

}