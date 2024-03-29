package com.dfcruz.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cat_breed")
data class CatBreedEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val temperament: String,
    val origin: String,
    val description: String,
    val countryCodes: String,
    val countryCode: String,
    val lifeSpan: String,
    val wikipediaUrl: String?,
    val favourite: Boolean,
    @Embedded(prefix = "image_")
    val imageEntity: ImageEntity,
    @Embedded(prefix = "weight_")
    val weight: MassUnitEntity
)