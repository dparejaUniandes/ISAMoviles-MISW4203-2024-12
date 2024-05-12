package com.example.vinilosapp.repository

import android.app.Application
import com.example.vinilosapp.broker.CollectorBroker
import com.example.vinilosapp.models.Collector

class CollectorRepository (val application: Application){
    suspend fun refreshData() : Result<List<Collector>> {
        return CollectorBroker.getInstance(application).getCollectors()
    }
}
