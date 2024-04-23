package com.movil.vinilosapp

import com.movil.vinilosapp.models.service.VinilosService
import com.movil.vinilosapp.models.repository.VinilosRepository

import android.app.Application

class VinilosApplication: Application() {

    private val URL_BACK: String = "http://35.193.69.44:3000/"

    val vinilosService by lazy { VinilosService.create(URL_BACK) }
    val vinilosRepository by lazy { VinilosRepository.create(vinilosService) }
}