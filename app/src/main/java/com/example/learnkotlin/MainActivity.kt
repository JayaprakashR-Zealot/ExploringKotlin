package com.example.learnkotlin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnKotlinFeatures.setOnClickListener { startActivity(Intent(this, KotlinFeaturesActivity::class.java)) }
        btnCallingWebservice.setOnClickListener { startActivity(Intent(this, HomeActivity::class.java)) }
        btnRetroRoom.setOnClickListener { startActivity(Intent(this, RetroRoomActivity::class.java)) }
        btnViewpager.setOnClickListener { startActivity(Intent(this, ViewPagerActivity::class.java)) }
    }
}
