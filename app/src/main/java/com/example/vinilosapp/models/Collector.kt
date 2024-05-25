package com.example.vinilosapp.models

import com.google.gson.annotations.SerializedName

data class Collector (
    @SerializedName("id")
    val collectorId: Int = 0,
    @SerializedName("name")
    val name:String = "",
    @SerializedName("telephone")
    val telephone:String = "",
    @SerializedName("email")
    val email:String = ""
)