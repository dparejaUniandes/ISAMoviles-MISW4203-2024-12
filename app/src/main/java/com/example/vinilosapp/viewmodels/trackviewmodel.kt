package com.example.vinilosapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.vinilosapp.models.Track
import com.example.vinilosapp.repository.TrackRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TrackViewModel(application: Application) :  AndroidViewModel(application) {

    private val trackRepository = TrackRepository(application)
    private val _tracks = MutableLiveData<List<Track>>()

    val tracks: LiveData<List<Track>>
        get() = _tracks

    private var _eventNetworkError = MutableLiveData(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        viewModelScope.launch(Dispatchers.Default) {
            withContext(Dispatchers.IO) {
                trackRepository.refreshData().onSuccess {
                    _tracks.postValue(it)
                    _eventNetworkError.postValue(false)
                    _isNetworkErrorShown.postValue(false)
                }.onFailure {
                    Log.d("Error", it.toString())
                    _eventNetworkError.postValue(true)
                }
            }
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TrackViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return TrackViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}