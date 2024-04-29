package com.example.vinilosapp.service

import com.example.vinilosapp.models.Comment
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentService {
    @GET("albums/{albumId}/comments")
    fun getComments(@Path("albumId") albumId : Int):
            Call<List<Comment>>
}