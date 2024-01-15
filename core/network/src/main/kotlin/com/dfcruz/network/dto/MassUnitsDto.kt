package com.dfcruz.network.dto

import com.google.gson.annotations.SerializedName

data class MassUnitsDto(
    @SerializedName("imperial")
    val imperial: String,
    @SerializedName("metric")
    val metric: String,
)