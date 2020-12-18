package com.example.jesusapp.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jesusapp.data.model.DairyCategoriesModelItem
import com.example.jesusapp.data.model.HomeDataModel1Item
import com.example.jesusapp.data.model.PrayerModelItem
import com.example.jesusapp.data.model.Users

@Database(
    entities = [(Users::class), (HomeDataModel1Item::class), (DairyCategoriesModelItem::class), (PrayerModelItem::class)],
    version = 1,
    exportSchema = false
)
abstract class BibleDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun diaryDao(): DiaryDao

    abstract fun prayerDao(): PrayerDao
}
