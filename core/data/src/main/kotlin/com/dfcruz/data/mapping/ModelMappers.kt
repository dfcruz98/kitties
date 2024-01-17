package com.dfcruz.data.mapping

import com.dfcruz.database.entity.BreedEntity
import com.dfcruz.model.Breed
import com.dfcruz.model.Image
import com.dfcruz.model.MassUnit

fun BreedEntity.toBreed(): Breed = Breed(
    id = this.id,
    name = this.name,
    temperament = this.temperament,
    origin = this.origin,
    description = this.description,
    countryCodes = this.countryCodes,
    countryCode = this.countryCode,
    lifeSpan = this.lifeSpan,
    wikipediaUrl = this.wikipediaUrl,
    favourite = this.favourite,
    image = Image(
        id = this.imageEntity.id,
        width = this.imageEntity.width,
        height = this.imageEntity.height,
        url = this.imageEntity.url,
    ),
    weight = MassUnit(
        imperial = this.weight.imperial,
        metric = this.weight.metric
    )
)