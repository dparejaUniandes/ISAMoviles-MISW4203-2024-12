package com.example.vinilosapp.repository

import android.app.Application
import com.example.vinilosapp.broker.TrackBroker
import com.example.vinilosapp.models.Track

class TrackRepository(val application: Application){
    suspend fun refreshData() : Result<List<Track>> {
        return TrackBroker.getInstance(application).getTracks()
    }

    suspend fun refreshData(trackId: (Int)) : Result<Track>{
        return TrackBroker.getInstance(application).getTrack(trackId)
    }
}