package com.dfcruz.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breed")
data class BreedEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String
)