package com.example.vinilosapp.broker

import android.content.Context
import com.example.vinilosapp.models.Collector
<<<<<<< HEAD
=======
import retrofit2.Call
import retrofit2.Callback
>>>>>>> ce07bc8312cb315ef38376dd194d7ef1b3a0e35b
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
<<<<<<< HEAD
        return try {
            val collectors = VinilosApi.collectorService.getCollectors()
            Result.success(collectors)
        } catch (e : Exception) {
            Result.failure(e)
=======
        try {
            var collectors = VinilosApi.collectorService.getCollectors()
            return Result.success(collectors)
        } catch (e : Exception) {
            return Result.failure(e)
>>>>>>> ce07bc8312cb315ef38376dd194d7ef1b3a0e35b
        }
    }

}