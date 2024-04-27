package com.example.vinilosapp.broker

import android.content.Context
import android.util.Log
import com.example.vinilosapp.models.Collector
import retrofit2.Call
import retrofit2.Callback

class CollectorBroker constructor(context: Context) {
    companion object{
        var instance: CollectorBroker? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CollectorBroker(context).also {
                    instance = it
                }
            }
    }

    fun getCollectors(onResponse:(resp:List<Collector>)->Unit, onFailure:(resp:String)->Unit) {
        var r = VinilosApi.collectorService.getCollectors()
        var p = r.enqueue(
            object : Callback<List<Collector>> {
                override fun onFailure(call: Call<List<Collector>>, t: Throwable) {
                    onFailure(t.message!!)
                }
                override fun onResponse(call: Call<List<Collector>>, response: retrofit2.Response<List<Collector>>) {
                    Log.d("Body Collectors", response.body().toString())
                    onResponse(response.body()!!)

                }
            })
    }

}