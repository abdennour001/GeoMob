package com.example.geomob

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.geomob.models.Country
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CountryTest {

    private var countryDB: CountryDB? = null
    private var countryDao: CountryDAO? = null

    @Before
    fun setup() {
        CountryDB.TEST_MODE = true
        countryDB = CountryDB.getInstance(InstrumentationRegistry.getInstrumentation().context)
        countryDao = countryDB?.countryDAO()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun should_Insert_Country_Item() {
        val algeria = Country(1, "Algeria", "Hello to algeria...", "flag", 2000000.0, 40000000, listOf("Gaz", "Petrol"), listOf("Algerian"), listOf("image1", "image2"))
        countryDao?.insert(algeria)
        val countryTest = countryDao?.getCountry(algeria.id)
        Assert.assertEquals(algeria.description, countryTest?.description)
    }

    @Test
    fun should_Flush_All_Data(){
        countryDao?.flushCountryData()
        Assert.assertEquals(0, countryDao?.getCountriesCount())
    }

    @Test
    fun should_Insert_Country_List(){
        val list = ArrayList<Country>()
        list.add(Country(1, "Algeria", "", "flag", 2000000.0, 40000000, listOf("Gaz", "Petrol"), listOf("Algerian"), listOf("image1", "image2")))
        list.add(Country(2, "Spain", "", "flag", 2000000.0, 40000000, listOf("Brain"), listOf("Spanish"), listOf("image1", "image2")))
        list.add(Country(3, "Moroco", "", "flag", 2000000.0, 40000000, listOf("Gaz", "Petrol"), listOf("Moroco"), listOf("image1", "image2")))

        countryDao?.insertCountryList(list)
        Assert.assertEquals(3, countryDao?.getCountriesCount())
    }
}