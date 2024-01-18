package com.dfcruz.network.dto

import com.google.gson.annotations.SerializedName

data class CatImageDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("breeds")
    val breeds: List<CatBreedDto>
)


