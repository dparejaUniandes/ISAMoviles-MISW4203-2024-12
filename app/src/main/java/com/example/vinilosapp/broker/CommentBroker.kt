package com.example.vinilosapp.broker

import android.content.Context
import android.util.Log
import com.example.vinilosapp.models.Album
import com.example.vinilosapp.models.Comment
import retrofit2.Call
import retrofit2.Callback

class CommentBroker constructor(context: Context) {
    companion object{
        var instance: CommentBroker? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CommentBroker(context).also {
                    instance = it
                }
            }
    }

    fun getComments(albumId:Int, onResponse:(resp:List<Comment>)->Unit, onFailure:(resp:String)->Unit) {
        var r = VinilosApi.commentService.getComments(albumId)
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