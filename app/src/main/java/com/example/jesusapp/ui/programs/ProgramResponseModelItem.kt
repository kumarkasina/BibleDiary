package com.example.jesusapp.ui.programs

data class ProgramResponseModelItem(
    val created_at: String,
    val description: String,
    val image: String,
    val program_id: Int,
    val topic_name: String,
    val updated_at: String
)