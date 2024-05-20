package com.example.vinilosapp.models

import com.google.gson.annotations.SerializedName

data class Track (
    @SerializedName("id")
    val trackId:Int = 0,
    @SerializedName("name")
    val name:String = "",
    @SerializedName("duration")
    val duration:String = ""

)