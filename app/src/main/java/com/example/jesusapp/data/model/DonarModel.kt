package com.example.jesusapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Donars")

data class DonarModelItem(
    @PrimaryKey val donor_id: Int,
    val donor_name: String,
    val mobile_no: String,
    val email: String,
    val designation: String,
    val city: String,
    val state: String,
    val country: String,
    val address: String,
    val amount: Int,
    val comments: String?,
    val image: String,
    val status: String,
    val created_at: String,
    val updated_at: String
)