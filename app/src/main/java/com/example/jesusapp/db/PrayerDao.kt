package com.example.jesusapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jesusapp.data.model.PrayerModelItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
abstract class PrayerDao {

    @Query("SELECT * FROM Prayers")
    abstract fun getAllPrayers(): Flow<List<PrayerModelItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPrayers(prayers: List<PrayerModelItem>)

    @Query("DELETE FROM Prayers")
    abstract fun deleteAllPrayers()

    @ExperimentalCoroutinesApi
    fun getAllPrayerDistinctUntilChanged() =
        getAllPrayers().distinctUntilChanged()

}