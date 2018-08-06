package com.example.learnkotlin.room_database

import com.example.learnkotlin.Constants
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit



class ApiUtils {

    val BASE_URL = Constants.RETRO_BASE_URL
    private var retrofit: Retrofit? = null

    fun getClient(baseUrl: String): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit
    }

    fun getService() : IUser{
        return getClient(BASE_URL)!!.create(IUser::class.java)
    }
}