package com.dfcruz.data

import com.dfcruz.data.fake.FakeCatsBreedsDao
import com.dfcruz.data.fake.FakeCatsService
import com.dfcruz.data.repository.CatBreedsRepository
import com.dfcruz.data.repository.CatsRepositoryImpl
import com.dfcruz.database.entity.CatBreedEntity
import com.dfcruz.database.entity.ImageEntity
import com.dfcruz.database.entity.MassUnitEntity
import com.dfcruz.network.dto.CatBreedDto
import com.dfcruz.network.dto.CatImageDto
import com.dfcruz.network.dto.MassUnitsDto
import com.dfcruz.network.util.NetworkResult
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CatBreedsRepositoryTest {

    private lateinit var SUT: CatBreedsRepository

    private lateinit var catBreedsDao: FakeCatsBreedsDao
    private lateinit var catsService: FakeCatsService

    @Before
    fun setup() {
        catBreedsDao = FakeCatsBreedsDao()
        catsService = FakeCatsService()

        SUT = CatsRepositoryImpl(
            catsService = catsService,
            catBreedsDao = catBreedsDao,
        )
    }

    @Test
    fun get_breeds_empty_database() = runTest {
        catsService.setReturnValue(
            NetworkResult.Success(
                listOf(
                    CatImageDto(
                        id = "1",
                        width = 100,
                        height = 100,
                        url = "https://en.wikipedia.org/wiki/Abyssinian_(cat)",
                        breeds = listOf(
                            CatBreedDto(
                                weight = MassUnitsDto(
                                    imperial = "50",
                                    metric = "100"
                                ),
                                id = "d123",
                                name = "Abyssinian",
                                temperament = "Active, Energetic, Independent, Intelligent, Gentle",
                                origin = "Egypt",
                                description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
                                countryCodes = "EG",
                                countryCode = "EG",
                                lifeSpan = "14 - 15",
                                wikipediaUrl = "https://en.wikipedia.org/wiki/Abyssinian_(cat)",
                            )
                        )
                    )
                )
            )
        )
        val catBreeds = SUT.getBreeds()
        assertThat(catBreeds.toList().size).isEqualTo(1)
    }

    @Test
    fun get_breeds_not_empty_database() = runTest {
        val entities = listOf(
            CatBreedEntity(
                id = "1",
                name = "Abyssinian",
                temperament = "Active, Energetic, Independent, Intelligent, Gentle",
                origin = "Egypt",
                description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
                countryCodes = "EG",
                countryCode = "EG",
                lifeSpan = "14 - 15",
                wikipediaUrl = "https://en.wikipedia.org/wiki/Abyssinian_(cat)",
                favourite = false,
                imageEntity = ImageEntity(
                    id = "0XYvRd7oD",
                    width = 1204,
                    height = 1445,
                    url = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg"
                ),
                weight = MassUnitEntity(
                    imperial = "7  -  10",
                    metric = "3 - 5"
                )
            ),
            CatBreedEntity(
                id = "2",
                name = "Abyssinian",
                temperament = "Active, Energetic, Independent, Intelligent, Gentle",
                origin = "Egypt",
                description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
                countryCodes = "EG",
                countryCode = "EG",
                lifeSpan = "14 - 15",
                wikipediaUrl = "https://en.wikipedia.org/wiki/Abyssinian_(cat)",
                favourite = true,
                imageEntity = ImageEntity(
                    id = "0XYvRd7oD",
                    width = 1204,
                    height = 1445,
                    url = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg"
                ),
                weight = MassUnitEntity(
                    imperial = "7  -  10",
                    metric = "3 - 5"
                )
            )
        )

        catBreedsDao.deleteAll()
        catBreedsDao.insertAll(entities)

        val catBreeds = SUT.getBreeds()
        assertThat(catBreeds.toList().size).isEqualTo(2)
    }

    @Test
    fun get_breed_by_id() = runTest {
        val entities = listOf(
            CatBreedEntity(
                id = "1",
                name = "Abyssinian",
                temperament = "Active, Energetic, Independent, Intelligent, Gentle",
                origin = "Egypt",
                description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
                countryCodes = "EG",
                countryCode = "EG",
                lifeSpan = "14 - 15",
                wikipediaUrl = "https://en.wikipedia.org/wiki/Abyssinian_(cat)",
                favourite = false,
                imageEntity = ImageEntity(
                    id = "0XYvRd7oD",
                    width = 1204,
                    height = 1445,
                    url = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg"
                ),
                weight = MassUnitEntity(
                    imperial = "7  -  10",
                    metric = "3 - 5"
                )
            ),
            CatBreedEntity(
                id = "2",
                name = "Abyssinian",
                temperament = "Active, Energetic, Independent, Intelligent, Gentle",
                origin = "Egypt",
                description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
                countryCodes = "EG",
                countryCode = "EG",
                lifeSpan = "14 - 15",
                wikipediaUrl = "https://en.wikipedia.org/wiki/Abyssinian_(cat)",
                favourite = true,
                imageEntity = ImageEntity(
                    id = "0XYvRd7oD",
                    width = 1204,
                    height = 1445,
                    url = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg"
                ),
                weight = MassUnitEntity(
                    imperial = "7  -  10",
                    metric = "3 - 5"
                )
            )
        )

        catBreedsDao.deleteAll()
        catBreedsDao.insertAll(entities)

        val catBreed = SUT.getBreed("2").first()
        assertThat(catBreed.id).isEqualTo("2")
    }

    @Test
    fun get_favourite_breeds() = runTest {
        val entities = listOf(
            CatBreedEntity(
                id = "1",
                name = "Abyssinian",
                temperament = "Active, Energetic, Independent, Intelligent, Gentle",
                origin = "Egypt",
                description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
                countryCodes = "EG",
                countryCode = "EG",
                lifeSpan = "14 - 15",
                wikipediaUrl = "https://en.wikipedia.org/wiki/Abyssinian_(cat)",
                favourite = false,
                imageEntity = ImageEntity(
                    id = "0XYvRd7oD",
                    width = 1204,
                    height = 1445,
                    url = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg"
                ),
                weight = MassUnitEntity(
                    imperial = "7  -  10",
                    metric = "3 - 5"
                )
            ),
            CatBreedEntity(
                id = "2",
                name = "Abyssinian",
                temperament = "Active, Energetic, Independent, Intelligent, Gentle",
                origin = "Egypt",
                description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
                countryCodes = "EG",
                countryCode = "EG",
                lifeSpan = "14 - 15",
                wikipediaUrl = "https://en.wikipedia.org/wiki/Abyssinian_(cat)",
                favourite = true,
                imageEntity = ImageEntity(
                    id = "0XYvRd7oD",
                    width = 1204,
                    height = 1445,
                    url = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg"
                ),
                weight = MassUnitEntity(
                    imperial = "7  -  10",
                    metric = "3 - 5"
                )
            )
        )

        catBreedsDao.deleteAll()
        catBreedsDao.insertAll(entities)

        val catBreeds = SUT.getFavouriteBreeds().toList().flatten()
        assertThat(catBreeds.size).isEqualTo(1)
        assertThat(catBreeds.first().id).isEqualTo("2")
    }

    @Test
    fun add_breed_to_favourites() = runTest {
        val id = "1"

        val entity = CatBreedEntity(
            id = id,
            name = "Abyssinian",
            temperament = "Active, Energetic, Independent, Intelligent, Gentle",
            origin = "Egypt",
            description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
            countryCodes = "EG",
            countryCode = "EG",
            lifeSpan = "14 - 15",
            wikipediaUrl = "https://en.wikipedia.org/wiki/Abyssinian_(cat)",
            favourite = false,
            imageEntity = ImageEntity(
                id = "0XYvRd7oD",
                width = 1204,
                height = 1445,
                url = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg"
            ),
            weight = MassUnitEntity(
                imperial = "7  -  10",
                metric = "3 - 5"
            )
        )

        catBreedsDao.deleteAll()
        catBreedsDao.insert(entity)

        SUT.toggleBreedFavourite(id)
        val catBreed = SUT.getBreed(id).first()

        assertThat(catBreed.id).isEqualTo(id)
        assertThat(catBreed.favourite).isEqualTo(true)
    }

    @Test
    fun remove_breed_from_favourites() = runTest {
        val id = "1"

        val entity = CatBreedEntity(
            id = id,
            name = "Abyssinian",
            temperament = "Active, Energetic, Independent, Intelligent, Gentle",
            origin = "Egypt",
            description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
            countryCodes = "EG",
            countryCode = "EG",
            lifeSpan = "14 - 15",
            wikipediaUrl = "https://en.wikipedia.org/wiki/Abyssinian_(cat)",
            favourite = true,
            imageEntity = ImageEntity(
                id = "0XYvRd7oD",
                width = 1204,
                height = 1445,
                url = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg"
            ),
            weight = MassUnitEntity(
                imperial = "7  -  10",
                metric = "3 - 5"
            )
        )

        catBreedsDao.deleteAll()
        catBreedsDao.insert(entity)

        SUT.toggleBreedFavourite(id)
        val catBreed = SUT.getBreed(id).first()
        assertThat(catBreed.id).isEqualTo(id)
        assertThat(catBreed.favourite).isEqualTo(false)
    }

}