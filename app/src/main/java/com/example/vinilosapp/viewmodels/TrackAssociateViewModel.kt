package com.example.vinilosapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.vinilosapp.models.Album
import com.example.vinilosapp.models.Track
import com.example.vinilosapp.repository.AlbumRepository
import com.example.vinilosapp.repository.TrackRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TrackAssociateViewModel(application: Application, albumId: Int) :  AndroidViewModel(application) {

    private val albumRepository = AlbumRepository(application)
    private val _album = MutableLiveData<Album>()
    private val _tracks = MutableLiveData<List<Track>>()
    private val trackRepository = TrackRepository(application)
    val id:Int = albumId

    val albumTrack: LiveData<Album>
        get() = _album
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
                albumRepository.refreshData(id).onSuccess {
                    _album.postValue(it)
                    _eventNetworkError.postValue(false)
                    _isNetworkErrorShown.postValue(false)
                }.onFailure {
                    Log.d("Error", it.toString())
                    _eventNetworkError.postValue(true)
                }
            }
        }
    }

    fun refreshDataFromNetwork(name: String, duration: String) {
        viewModelScope.launch(Dispatchers.Default) {
            withContext(Dispatchers.IO) {
                trackRepository.refreshData(id, name, duration).onSuccess {
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

    class Factory(val app: Application, private val albumId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TrackAssociateViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return TrackAssociateViewModel(app, albumId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}