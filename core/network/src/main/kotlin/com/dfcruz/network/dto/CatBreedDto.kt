package com.dfcruz.network.dto

import com.google.gson.annotations.SerializedName

data class CatBreedDto(
    val weight: MassUnitsDto,
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("temperament")
    val temperament: String,
    @SerializedName("origin")
    val origin: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("country_codes")
    val countryCodes: String,
    @SerializedName("country_code")
    val countryCode: String,
    @SerializedName("life_span")
    val lifeSpan: String,
    @SerializedName("wikipedia_url")
    val wikipediaUrl: String,
)
