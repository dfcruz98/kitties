package com.dfcruz.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breed_page")
class BreedPageEntity(
    @PrimaryKey(autoGenerate = false)
    val breedId: String,
    val previousPage: Int?,
    val nextPage: Int?,
)