package com.example.vinilosapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.vinilosapp.repository.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlbumCreateViewModel(application: Application) :  AndroidViewModel(application) {

    private val albumRepository = AlbumRepository(application)

    private var _eventNetworkError = MutableLiveData(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    public fun refreshDataFromNetwork(bodyNewAlbum: Map<String, String>) {
        viewModelScope.launch(Dispatchers.Default) {
            withContext(Dispatchers.IO) {
                Log.d("Body in view model", bodyNewAlbum.toString())
                albumRepository.refreshData(bodyNewAlbum).onSuccess {
                    Log.d("Body", it.toString())
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

    class Factory(val app: Application ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlbumCreateViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumCreateViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}