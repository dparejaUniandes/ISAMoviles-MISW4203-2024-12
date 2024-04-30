package com.example.vinilosapp.models

import com.google.gson.annotations.SerializedName
import java.lang.reflect.Constructor

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
    val birthDate:String = ""
)