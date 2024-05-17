package com.example.vinilosapp.broker

import android.content.Context
import androidx.collection.LruCache
import com.example.vinilosapp.models.Artist

class CacheManager(context: Context) {
    companion object{
        private var instance: CacheManager? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CacheManager(context).also {
                    instance = it
                }
            }
    }
    private var artistDetail: LruCache<Int, Artist> = LruCache(3)
    fun addArtistDetail(artistId: Int, artistDetailNew: Artist){
        if (artistDetail[artistId] == null){
            artistDetail.put(artistId, artistDetailNew)
        }
    }
    fun getArtistDetail(artistId: Int) : Artist {
        return if (artistDetail[artistId]!=null) artistDetail[artistId]!! else Artist(artistId=-1)
    }
}