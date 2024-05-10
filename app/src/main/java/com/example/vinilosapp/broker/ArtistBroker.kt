package com.example.vinilosapp.broker

import android.content.Context
import android.util.Log
import com.example.vinilosapp.models.Artist
import retrofit2.Call
import retrofit2.Callback
import java.lang.Exception

class ArtistBroker constructor(context: Context) {
    companion object{
        var instance: ArtistBroker? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: ArtistBroker(context).also {
                    instance = it
                }
            }
    }

    fun getArtists(onResponse:(resp:List<Artist>)->Unit, onFailure:(resp:String)->Unit) {
        var r = VinilosApi.artistService.getArtists()
        var p = r.enqueue(
            object : Callback<List<Artist>> {
                override fun onFailure(call: Call<List<Artist>>, t: Throwable) {
                    onFailure(t.message!!)
                }

                override fun onResponse(call: Call<List<Artist>>, response: retrofit2.Response<List<Artist>>) {
                    Log.d("Body Artists", response.body().toString())
                    onResponse(response.body()!!)

                }
            })
    }

    suspend fun getArtist(artistId : (Int)) : Result<Artist> {
        try {
            val artist = VinilosApi.artistService.getArtist(artistId)
            return Result.success(artist)
        } catch (e : Exception) {
            return Result.failure(e)
        }
    }
}