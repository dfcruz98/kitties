package com.dfcruz.data.mapping

import com.dfcruz.database.entity.CatBreedEntity
import com.dfcruz.database.entity.ImageEntity
import com.dfcruz.database.entity.MassUnitEntity
import com.dfcruz.network.dto.CatBreedDto
import com.dfcruz.network.dto.CatImageDto

fun List<CatImageDto>.toBreedsEntity(): List<CatBreedEntity> = this
    .map { image -> image.breeds.map { breed -> breed.toBreedEntity(image) } }
    .flatten()

fun CatImageDto.toBreedsEntity(): List<CatBreedEntity> {
    return this.breeds.map { breed -> breed.toBreedEntity(this) }
}


fun CatBreedDto.toBreedEntity(catImageDto: CatImageDto): CatBreedEntity = CatBreedEntity(
    id = this.id,
    name = this.name,
    temperament = this.temperament,
    origin = this.origin,
    description = this.description,
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