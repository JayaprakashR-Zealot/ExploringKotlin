package com.example.learnkotlin.room_database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "UserTable")
data class User(@PrimaryKey(autoGenerate = true) var id: Long?,
                @ColumnInfo(name = "FirstName") var fname: String,
                @ColumnInfo(name = "LastName") var lname: String,
                @ColumnInfo(name = "Photo") var image: String
)

