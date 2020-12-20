package com.example.jesusapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jesusapp.data.model.DonarModelItem

@Dao
abstract  class DonarDao{
    @Query("SELECT * FROM Donars")
    abstract suspend fun getDonars(): List<DonarModelItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertDonars(donars: List<DonarModelItem>)

    @Query("DELETE FROM Donars")
    abstract fun deleteAllDonars()
}