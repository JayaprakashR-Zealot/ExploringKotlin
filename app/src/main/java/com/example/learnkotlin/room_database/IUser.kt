package com.example.learnkotlin.room_database

import com.example.learnkotlin.model.RetroUser
import retrofit2.Call
import retrofit2.http.GET

interface IUser{
    @GET ("users")
    fun getAllUsers() : Call<RetroUser>
}