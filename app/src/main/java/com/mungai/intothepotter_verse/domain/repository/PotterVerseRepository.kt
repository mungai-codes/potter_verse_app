package com.mungai.intothepotter_verse.domain.repository

import com.mungai.intothepotter_verse.common.Resource
import com.mungai.potterpedia.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface PotterVerseRepository {

    fun getAllCharacters(): Flow<Resource<List<Character>>>
}