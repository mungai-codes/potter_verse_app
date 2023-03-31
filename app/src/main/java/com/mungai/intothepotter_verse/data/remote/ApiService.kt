package com.mungai.intothepotter_verse.data.remote

import com.mungai.potterpedia.data.remote.dto.CharacterDto
import retrofit2.http.GET

interface ApiService {

    @GET("characters")
    suspend fun getAllCharacters(): List<CharacterDto>

}