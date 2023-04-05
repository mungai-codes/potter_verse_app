package com.mungai.intothepotter_verse.data.remote

import com.mungai.intothepotter_verse.data.remote.dto.SpellDto
import com.mungai.potterpedia.data.remote.dto.CharacterDto
import retrofit2.http.GET

// Defines the endpoints for making api requests to retrieve data from the api.
interface ApiService {

    /**
     * This function sends a GET request to the "characters" endpoint and retrieves a list of CharacterDto objects
     * from the API. The function is annotated with @GET("characters") to specify the endpoint of the request.
     * The function is marked with the suspend keyword to allow it to be called from a coroutine.
     */
    @GET("characters")
    suspend fun getAllCharacters(): List<CharacterDto>

    /**
     * Fetches all spells from the remote API using a GET request to the "spells" endpoint.
     * @return A list of [SpellDto] objects representing the fetched spells.
     */
    @GET("spells")
    suspend fun getAllSpells(): List<SpellDto>

}