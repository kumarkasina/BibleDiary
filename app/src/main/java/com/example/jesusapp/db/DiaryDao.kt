package com.example.jesusapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jesusapp.data.model.DairyCategoriesModelItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
abstract class DiaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCatergories(dairyCategoriesModelItem: List<DairyCategoriesModelItem>)

    @Query("SELECT * FROM DiaryCategories")
    abstract fun getAllCategories(): Flow<List<DairyCategoriesModelItem>>

    @Query("DELETE FROM DiaryCategories")
    abstract fun deleteAllUsers()


    @ExperimentalCoroutinesApi
    fun getAllCategDistinctUntilChanged() =
        getAllCategories().distinctUntilChanged()

}