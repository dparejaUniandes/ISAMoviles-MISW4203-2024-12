package com.movil.vinilosapp.models.repository

import com.movil.vinilosapp.models.dto.AlbumDto
import com.movil.vinilosapp.models.service.VinilosService

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VinilosRepository(private val vinilosService:VinilosService) {

    companion object Factory {
        fun create(vinilosService:VinilosService): VinilosRepository {
            return VinilosRepository(vinilosService)
        } // End create
    } // End Factory

    fun getAlbums(onResponse:(resp:ArrayList<AlbumDto>)->Unit, onFailure:(resp:String)->Unit) {

        vinilosService.getAlbum().getAlbumList()
            .enqueue(
                object : Callback<ArrayList<AlbumDto>> {
                    override fun onResponse(
                        call: Call<ArrayList<AlbumDto>>,
                        response: Response<ArrayList<AlbumDto>>,
                    ) {
                        onResponse(response.body()!!)
                    }
                },
            )
    } // End getAlbums

} // End class