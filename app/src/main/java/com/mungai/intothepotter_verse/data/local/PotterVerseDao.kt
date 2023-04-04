package com.mungai.intothepotter_verse.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mungai.intothepotter_verse.data.local.entity.CharacterEntity
import com.mungai.intothepotter_verse.data.local.entity.SpellEntity

@Dao
interface PotterVerseDao {

    // inserts character data into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharacterEntity>)

    @Query("SELECT * FROM character_table")
    suspend fun getAllCharacters(): List<CharacterEntity>

    @Query("SELECT * FROM CHARACTER_TABLE WHERE id = :characterId")
    suspend fun getCharacterById(characterId: String): CharacterEntity

    @Delete
    suspend fun deleteCharacter(character: CharacterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpells(spells: List<SpellEntity>)

    @Query("SELECT * from spell_table")
    suspend fun getAllSpells(): List<SpellEntity>

    @Query("SELECT * FROM spell_table WHERE id = :id")
    suspend fun getSpellById(id: String): SpellEntity

    @Query("SELECT * FROM character_table WHERE image != ''")
    suspend fun getMainCast(): List<CharacterEntity>

    @Query("SELECT * FROM character_table WHERE hogwartsStudent = 1")
    suspend fun getStudents(): List<CharacterEntity>

    @Query("SELECT * FROM character_table WHERE wizard = 1")
    suspend fun getWizards(): List<CharacterEntity>

    @Query("SELECT * FROM character_table WHERE hogwartsStaff = 1")
    suspend fun getStaff(): List<CharacterEntity>

    @Query("SELECT * FROM character_table WHERE house = :house")
    suspend fun getCharactersByHouse(house: String): List<CharacterEntity>

    @Query("SELECT * FROM character_table WHERE name LIKE '%' || :name || '%'")
    suspend fun getCharactersByName(name: String): List<CharacterEntity>

    @Query("SELECT * FROM character_table WHERE house = :house AND name LIKE '%' || :name || '%'")
    suspend fun getCharactersByHouseAndName(house: String, name: String): List<CharacterEntity>



}