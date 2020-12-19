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
    val icon: String,
    var selected: Boolean,
    val type: String,
    val heading: String,
    val message: String,
    val date: String,
    val audio: String


)