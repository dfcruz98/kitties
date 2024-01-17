package com.dfcruz.model

import androidx.compose.runtime.Immutable

@Immutable
data class Breed(
    val id: String,
    val name: String,
    val temperament: String,
    val origin: String,
    val description: String,
    val countryCodes: String,
    val countryCode: String,
    val lifeSpan: String,
    val favourite: Boolean = false,
    val wikipediaUrl: String?,
    val image: Image,
    val weight: MassUnit
)
