package com.example.learnkotlin.mvp.model

import com.example.learnkotlin.mvp.presenter.IBasePresenter
import com.example.learnkotlin.mvp.view.IBaseView

class BaseModel(private val baseView: IBaseView) : IBasePresenter {

    override fun clickMe() {
        baseView.clickSuccess("Hello, Kotlinzers!!!")
    }

    companion object {
        val helloTag = "Hello,World!"
    }
}