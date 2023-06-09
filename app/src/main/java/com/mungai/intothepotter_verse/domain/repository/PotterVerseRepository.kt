package com.mungai.intothepotter_verse.domain.repository

import com.mungai.intothepotter_verse.common.Resource
import com.mungai.intothepotter_verse.domain.model.Spell
import com.mungai.potterpedia.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface PotterVerseRepository {

    fun getSpells(): Flow<Resource<List<Spell>>>

    fun getMainCast(): Flow<Resource<List<Character>>>

    fun getStaff(): Flow<Resource<List<Character>>>

    fun getStudents(): Flow<Resource<List<Character>>>

    fun getAllCharacters(): Flow<Resource<List<Character>>>

    fun getCharacterById(characterId: String): Flow<Resource<Character>>

    fun getCharactersByHouse(house: String): Flow<Resource<List<Character>>>

    fun getCharactersByName(name: String): Flow<Resource<List<Character>>>

    fun getCharactersByNameAndHouse(house: String, name: String): Flow<Resource<List<Character>>>

    fun getSpellById(id: String): Flow<Resource<Spell>>
}