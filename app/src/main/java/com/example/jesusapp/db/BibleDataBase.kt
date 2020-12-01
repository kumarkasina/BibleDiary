package com.example.jesusapp.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jesusapp.data.model.Users

@Database(entities = [(Users::class)], version = 1,exportSchema = false)
abstract class BibleDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao

}
