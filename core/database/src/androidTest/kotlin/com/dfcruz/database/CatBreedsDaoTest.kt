package com.dfcruz.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dfcruz.database.dao.CatBreedsDao
import com.dfcruz.database.entity.CatBreedEntity
import com.dfcruz.database.entity.ImageEntity
import com.dfcruz.database.entity.MassUnitEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CatBreedsDaoTest {

    private lateinit var catBreedsDao: CatBreedsDao
    private lateinit var db: KittiesDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, KittiesDatabase::class.java
        ).build()
        catBreedsDao = db.catBreedsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun get_favourites() = runTest {
        val entities = listOf(
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
        catBreedsDao.insertAll(entities)
        val favourites = catBreedsDao.getFavourites().toList().flatten()
        assertThat(favourites).hasSize(1)
    }
}