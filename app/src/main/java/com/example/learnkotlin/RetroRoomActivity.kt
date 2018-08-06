package com.example.learnkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.learnkotlin.model.RetroUser
import com.example.learnkotlin.room_database.ApiUtils
import com.example.learnkotlin.room_database.IUser
import kotlinx.android.synthetic.main.activity_retro_room.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RetroRoomActivity : AppCompatActivity() {

    private val TAG: String = "RetroRoomActivity"
    private lateinit var userService: IUser

    val userList: ArrayList<RetroUser?> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retro_room)

        init()


        imgRetroButtonClose.setOnClickListener {
            finish()
        }
        loadUsers()
    }

    fun init() {
        val api = ApiUtils()
        userService = api.getService()
    }

    fun loadUsers() {

        userService.getAllUsers().enqueue(object : Callback<RetroUser> {
            override fun onResponse(call: Call<RetroUser>?, response: Response<RetroUser>?) {

                if (response != null && response.isSuccessful) {
                    //pokemonListListener.onSuccess(response.body())
                    userList.add(response.body())
                    showList()
                } else {
                    Log.i(TAG, "Failure response")
                }
            }

            override fun onFailure(call: Call<RetroUser>?, t: Throwable?) {
                Log.e(TAG, "Error:")
                //pokemonListListener.onFailure(appContext.getString(R.string.error_fetching_data))
            }
        })
    }

    fun showList() {
        val layoutManager = LinearLayoutManager(this)
        recyclerViewRetroUser.setLayoutManager(layoutManager)
        /*recyclerViewRetroUser.adapter =  RecyclerAdapter(userList, this@RetroRoomActivity)
        recyclerViewRetroUser.setHasFixedSize(true)
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerViewRetroUser.addItemDecoration(itemDecoration)*/
    }

}
