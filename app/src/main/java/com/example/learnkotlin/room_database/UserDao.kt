package com.example.learnkotlin.room_database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

@Dao
interface IUserDao {

    @Query("SELECT * FROM USERTABLE")
    fun getAllUsers(): List<User>

    @Insert(onConflict = REPLACE)
    fun insert(userData : User)

    @Delete
    fun delete(userData : User)

    @Query("select * from USERTABLE where id = :id")
    fun findUserById(id: Long): User
}
