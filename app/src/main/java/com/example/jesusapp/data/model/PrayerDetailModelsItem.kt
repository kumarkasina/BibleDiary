package com.example.jesusapp.data.model

data class PrayerDetailModelsItem(
    val cat_id: String,
    val created_at: String,
    val message: String,
    val prayer_id: Int,
    val prayer_type: String,
    val updated_at: String,
    var expanded: Boolean
)