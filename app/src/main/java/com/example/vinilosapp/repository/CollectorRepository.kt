package com.example.vinilosapp.repository

import android.app.Application
import com.example.vinilosapp.broker.CollectorBroker
import com.example.vinilosapp.models.Collector

class CollectorRepository (val application: Application){
    fun refreshData(callback: (List<Collector>)->Unit, onFailure:(String)->Unit) {
        CollectorBroker.getInstance(application).getCollectors({
            callback(it)
        },
            onFailure
        )
    }
}
