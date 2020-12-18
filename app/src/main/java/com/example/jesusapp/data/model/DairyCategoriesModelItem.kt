package com.example.jesusapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DiaryCategories")
data class DairyCategoriesModelItem(

    @PrimaryKey val cat_id: Int,
    val created_at: String,
    val name: String,
    val status: Int,
    val updated_at: String,
    var selected: Boolean
)