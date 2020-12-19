package com.example.jesusapp.db

import androidx.room.Embedded
import androidx.room.Relation
import com.example.jesusapp.data.model.DairyCategoriesModelItem
import com.example.jesusapp.data.model.DairyMessageItem

data class CategoryandMessage(
    @Embedded val d1: DairyCategoriesModelItem,
    @Relation(
        parentColumn = "cat_id",
        entityColumn = "cat_id",

        )
    val d2: DairyMessageItem

)