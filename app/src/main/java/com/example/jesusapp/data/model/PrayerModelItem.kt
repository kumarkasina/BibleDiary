package com.example.jesusapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Prayers")
data class PrayerModelItem(
    @PrimaryKey val cat_id: Int,
    val created_at: String,
    val icon: String,
    val name: String,
    val status: String,
    val updated_at: String,
    var selected: Boolean
)