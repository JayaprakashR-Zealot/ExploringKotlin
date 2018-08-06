package com.example.learnkotlin.model

import com.google.gson.annotations.SerializedName

data class RetroUser(@SerializedName("id") val id: Int,
                     @SerializedName("first_name") val first_name:String,
                     @SerializedName("last_name") val last_name:String,
                     @SerializedName("avatar") val photo:String)

