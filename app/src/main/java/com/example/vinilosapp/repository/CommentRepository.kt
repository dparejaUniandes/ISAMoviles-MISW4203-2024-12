package com.example.vinilosapp.repository

import android.app.Application
import com.example.vinilosapp.broker.CommentBroker
import com.example.vinilosapp.models.Comment

class CommentRepository (val application: Application){
    fun refreshData(albumId: (Int), callback: (List<Comment>)->Unit, onFailure:(String)->Unit) {
        CommentBroker.getInstance(application).getComments(
            albumId,
            {
            callback(it)
            },
            onFailure
        )
    }
}
