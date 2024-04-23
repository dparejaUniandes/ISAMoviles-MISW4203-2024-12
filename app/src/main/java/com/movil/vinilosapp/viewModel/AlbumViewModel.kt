package com.movil.vinilosapp.viewModel

import com.movil.vinilosapp.models.dto.AlbumDto
import com.movil.vinilosapp.models.repository.VinilosRepository
import com.movil.vinilosapp.VinilosApplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

import androidx.compose.runtime.mutableStateOf

sealed interface AlbumUiState {
    data class Success(val albums: List<AlbumDto>) : AlbumUiState
    object Error : AlbumUiState
    object Loading : AlbumUiState
}

class AlbumViewModel(private val albumRepository: VinilosRepository): ViewModel() {

    var albumUiState: AlbumUiState by mutableStateOf(AlbumUiState.Loading)

    private var dataLoaded = false

    init {
        getAllAlbums()
    }

    private fun getAllAlbums() {

        if (dataLoaded) {
            return
        }

        viewModelScope.launch {

            try {
                albumRepository.getAlbums(
                    onResponse = {
                            albumList -> albumUiState = AlbumUiState.Success(albumList)
                    },
                    onFailure = {
                        albumUiState = AlbumUiState.Error
                    })
            } catch (e: Exception) {
                albumUiState = AlbumUiState.Error
            }

        } // End viewModelScope

    } // End getAllAlbums

    fun refreshAlbums() {
        albumUiState = AlbumUiState.Loading
        dataLoaded = false
        getAllAlbums()
    } // End refreshAlbums

    companion object {
        private var instance: AlbumViewModel? = null

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as VinilosApplication)
                val vinilosRepository = application.vinilosRepository
                AlbumViewModel(albumRepository = vinilosRepository)
                instance ?: AlbumViewModel(albumRepository = vinilosRepository).also { instance = it }
            }
        }
    } // End object

} // End class