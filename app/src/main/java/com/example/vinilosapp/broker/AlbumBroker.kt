package com.example.vinilosapp.broker

import android.content.Context
import android.util.Log
import com.example.vinilosapp.models.Album
import com.example.vinilosapp.models.Comment
import retrofit2.Call
import retrofit2.Callback

class AlbumBroker constructor(context: Context) {
    companion object{
        var instance: AlbumBroker? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: AlbumBroker(context).also {
                    instance = it
                }
            }
    }

    fun getAlbums(onResponse:(resp:List<Album>)->Unit, onFailure:(resp:String)->Unit) {
        var r = VinilosApi.albumService.getAlbums()
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

    fun getAlbum(albumId : (Int), onResponse:(resp:Album)->Unit, onFailure:(resp:String)->Unit) {
        var r = VinilosApi.albumService.getAlbum(albumId)
        var p = r.enqueue(
            object : Callback<Album> {
                override fun onFailure(call: Call<Album>, t: Throwable) {
                    onFailure(t.message!!)
                }

                override fun onResponse(call: Call<Album>, response: retrofit2.Response<Album>) {
                    Log.d("Body Album", response.body().toString())
                    onResponse(response.body()!!)

                }
            })
    }
}