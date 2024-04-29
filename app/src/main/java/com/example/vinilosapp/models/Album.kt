package com.example.vinilosapp.models

import com.google.gson.annotations.SerializedName
import java.lang.reflect.Constructor

data class Album (
    @SerializedName("id")
    val albumId:Int = 0,
    @SerializedName("name")
    val name:String = "",
    @SerializedName("cover")
    val cover:String = "",
    @SerializedName("releaseDate")
    val releaseDate:String = "",
    @SerializedName("description")
    val description:String = "",
    @SerializedName("genre")
    val genre:String = "",
    @SerializedName("recordLabel")
    val recordLabel:String = ""

)