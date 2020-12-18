package com.example.jesusapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Features")
data class HomeDataModel1Item(
    val created_at: String,
    val display_page_no: String,
    val feature_code: String,
    @PrimaryKey val feature_id: Int,
    val feature_name: String,
    val image: String,
    val sort_order: String,
    val status: String,
    val updated_at: String,
    var selected: Boolean
)