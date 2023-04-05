package com.mungai.intothepotter_verse.data.remote

import com.mungai.intothepotter_verse.ResponseFileReader
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.IOException
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit

class PotterVerseApiTests {

    private lateinit var apiService: ApiService
    private lateinit var server: MockWebServer
    private lateinit var response: String

    @Before
    fun setUp() {
        server = MockWebServer()
        server.start()
        val client = OkHttpClient
            .Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        apiService = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }

    @Test
    fun `the character endpoint should not return null`() {
        response = ResponseFileReader("character.json").content

        server.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(response)
        )

        Assert.assertNotNull(runBlocking { apiService.getAllCharacters() })
    }


    @Test
    fun `the spell endpoint should not return null`() {
        response = ResponseFileReader("spell.json").content

        server.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(response)
        )

        Assert.assertNotNull(runBlocking { apiService.getAllSpells() })
    }

    @Test
    fun `character JSON should be deserialized correctly`() {
        // raeding the character JSON response from a file
        response = ResponseFileReader("character.json").content

        // Enqueue a mock response from the server
        server.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(response)
        )

        // calling the API and retrieving the character response
        val characters = runBlocking { apiService.getAllCharacters() }

        // Verify that the API response is not null
        Assert.assertNotNull(characters)

        // Verify that the API response contains data
        Assert.assertTrue(characters.isNotEmpty())

        // Verify that the first character in the response has the expected data types
        val firstCharacter = characters.first()

        Assert.assertEquals("9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8", firstCharacter.id)
        Assert.assertEquals("Harry Potter", firstCharacter.name)
        Assert.assertNotEquals("31-16-1980", firstCharacter.dateOfBirth)
        Assert.assertEquals("male", firstCharacter.gender)
        Assert.assertEquals("human", firstCharacter.species)
        Assert.assertEquals("Gryffindor", firstCharacter.house)
        Assert.assertEquals("stag", firstCharacter.patronus)

    }

    @Test
    fun `spell JSON should be deserialized correctly`() {
        // Read the spell JSON response from a file
        val response = ResponseFileReader("spell.json").content

        // Enqueue a mock response from the server
        server.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(response)
        )

        // Call the API and retrieve the spell response
        val spells = runBlocking { apiService.getAllSpells() }

        // Verify that the API response is not null
        Assert.assertNotNull(spells)

        // Verify that the API response contains data
        Assert.assertTrue(spells.isNotEmpty())

        // Verify that the first spell in the response has the expected data types
        val firstSpell = spells.first()

        Assert.assertEquals("c76a2922-ba4c-4278-baab-44defb631236", firstSpell.id)
        Assert.assertEquals("Aberto", firstSpell.name)
        Assert.assertEquals("Opens locked doors", firstSpell.description)
    }

    @Test
    fun `error response from api should throw exception`() {

        response = ResponseFileReader("character.json").content

        // Enqueue a mock error response from the server
        server.enqueue(
            MockResponse().setResponseCode(HttpURLConnection.HTTP_NOT_FOUND).setBody(response)
        )

        // Call the API and catch the expected exception
        var exceptionThrown = false
        try {
            runBlocking { apiService.getAllCharacters() }
        } catch (e: Exception) {
            // Check if the exception is a common network exception
            exceptionThrown = e is IOException || e is HttpException
        }

        // Verify that an exception was thrown
        Assert.assertTrue(exceptionThrown)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }


}