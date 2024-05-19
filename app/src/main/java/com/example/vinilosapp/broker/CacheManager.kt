package com.example.vinilosapp.broker

import android.content.Context
import androidx.collection.LruCache
import com.example.vinilosapp.models.Album
import com.example.vinilosapp.models.Track
import com.example.vinilosapp.models.Artist
import com.example.vinilosapp.models.Collector

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
	
    private var albumDetail: LruCache<Int, Album> = LruCache(3)
	private var trackDetail: LruCache<Int, Track> = LruCache(3)
	private var artistDetail: LruCache<Int, Artist> = LruCache(3)
	private var collectorDetail: LruCache<Int, Collector> = LruCache(3)
	
    fun addAlbumDetail(albumId: Int, albumDetailNew: Album){
        if (albumDetail[albumId] == null){
            albumDetail.put(albumId, albumDetailNew)
        }
    }
    fun getAlbumDetail(albumId: Int) : Album {
        return if (albumDetail[albumId]!=null) albumDetail[albumId]!! else Album(albumId=-1)
    }
	
	fun addTrackDetail(trackId: Int, trackDetailNew: Track){
        if (trackDetail[trackId] == null){
            trackDetail.put(trackId, trackDetailNew)
        }
    }
    fun getTrackDetail(trackId: Int) : Track {
        return if (trackDetail[trackId]!=null) trackDetail[trackId]!! else Track(trackId=-1)
    }
	
	fun addArtistDetail(artistId: Int, artistDetailNew: Artist){
        if (artistDetail[artistId] == null){
            artistDetail.put(artistId, artistDetailNew)
        }
    }
    fun getArtistDetail(artistId: Int) : Artist {
        return if (artistDetail[artistId]!=null) artistDetail[artistId]!! else Artist(artistId=-1)
    }
}