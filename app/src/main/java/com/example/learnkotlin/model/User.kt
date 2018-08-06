package com.example.learnkotlin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(val id: Int,val first_name:String,val last_name:String,val photo:String) : Parcelable