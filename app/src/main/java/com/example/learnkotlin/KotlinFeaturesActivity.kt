package com.example.learnkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.learnkotlin.mvp.model.BaseModel
import com.example.learnkotlin.mvp.view.IBaseView
import kotlinx.android.synthetic.main.activity_kotlin_features.*


class KotlinFeaturesActivity : AppCompatActivity(), IBaseView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_features)

        btnTap.setOnClickListener { callTapMessage() }
    }

    fun callTapMessage() {
        val presenter = BaseModel(this)
        presenter.clickMe()
    }

    override fun clickSuccess(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}