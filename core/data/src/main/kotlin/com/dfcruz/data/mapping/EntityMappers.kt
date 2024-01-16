package com.dfcruz.data.mapping

import com.dfcruz.database.entity.BreedEntity
import com.dfcruz.database.entity.ImageEntity
import com.dfcruz.database.entity.MassUnitEntity
import com.dfcruz.network.dto.BreedDto
import com.dfcruz.network.dto.CatImageDto

fun List<CatImageDto>.toBreedsEntity(): List<BreedEntity> = this
    .map { image -> image.breeds.map { breed -> breed.toBreedEntity(image) } }
    .flatten()

fun BreedDto.toBreedEntity(catImageDto: CatImageDto): BreedEntity = BreedEntity(
    id = this.id,
    name = this.name,
    temperament = this.temperament,
    origin = this.origin,
    countryCodes = this.countryCodes,
    countryCode = this.countryCode,
    lifeSpan = this.lifeSpan,
    wikipediaUrl = this.wikipediaUrl,
    favourite = false,
    imageEntity = ImageEntity(
        id = catImageDto.id,
        width = catImageDto.width,
        height = catImageDto.height,
        url = catImageDto.url,
    ),
    weight = MassUnitEntity(
        imperial = this.weight.imperial,
        metric = this.weight.metric
    )
)