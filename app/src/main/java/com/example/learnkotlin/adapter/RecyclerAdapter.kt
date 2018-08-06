package com.example.learnkotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.learnkotlin.R
import com.example.learnkotlin.model.RetroUser
import com.example.learnkotlin.model.User
import kotlinx.android.synthetic.main.raw_adapter.view.*

class RecyclerAdapter(val items: ArrayList<User>, val context: Context) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClicked(position: Int, view: View)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.raw_adapter, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder?, position: Int) {
        Glide.with(context).load(items.get(position)!!.photo).into(holder?.imgUserPhoto)
        holder?.tvFName?.text = items.get(position)!!.first_name
        holder?.tvLName?.text = items.get(position)!!.last_name
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val imgUserPhoto = view.imgUserPhoto
        val tvFName = view.txtFirstName
        val tvLName = view.txtLastName
    }




}