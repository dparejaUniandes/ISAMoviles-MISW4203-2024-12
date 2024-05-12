package com.example.vinilosapp.models

import com.google.gson.annotations.SerializedName

data class Artist (
    @SerializedName("id")
    val artistId:Int = 0,
    @SerializedName("name")
    val name:String = "",
    @SerializedName("image")
    val image:String = "",
    @SerializedName("description")
    val description:String = "",
    @SerializedName("birthDate")
    var birthDate:String = "",
    @SerializedName("albums")
    val albums: List<Album> = listOf()
)