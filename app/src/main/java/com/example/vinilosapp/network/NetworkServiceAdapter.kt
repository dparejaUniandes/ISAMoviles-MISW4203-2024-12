package com.example.vinilosapp.network

import android.content.Context
import android.util.Log
import com.example.vinilosapp.models.Album
import com.example.vinilosapp.models.Collector
import com.example.vinilosapp.models.Comment
import retrofit2.Call
import retrofit2.Callback

class NetworkServiceAdapter constructor(context: Context) {
    companion object{
        const val BASE_URL= "http://10.0.2.2:3000/"
        var instance: NetworkServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapter(context).also {
                    instance = it
                }
            }
    }

    fun getAlbums(onResponse:(resp:List<Album>)->Unit, onFailure:(resp:String)->Unit) {
        var r = RetrofitApi.retrofitService.getAlbums()
        var p = r.enqueue(
            object : Callback<List<Album>> {
                override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                    onFailure(t.message!!)
                }

                override fun onResponse(call: Call<List<Album>>, response: retrofit2.Response<List<Album>>) {
                    Log.d("Body Albums", response.body().toString())
                    onResponse(response.body()!!)

                }
            })
    }

    fun getCollectors(onResponse:(resp:List<Collector>)->Unit, onFailure:(resp:String)->Unit) {
        var r = RetrofitApi.retrofitService.getCollectors()
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

    fun getComments(albumId:Int, onResponse:(resp:List<Comment>)->Unit, onFailure:(resp:String)->Unit) {
        var r = RetrofitApi.retrofitService.getComments(albumId)
        var p = r.enqueue(
            object : Callback<List<Comment>> {
                override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                    onFailure(t.message!!)
                }

                override fun onResponse(call: Call<List<Comment>>, response: retrofit2.Response<List<Comment>>) {
                    Log.d("Body Comments", response.body().toString())
                    onResponse(response.body()!!)

                }
            })
    }
}