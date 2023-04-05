package com.mungai.intothepotter_verse.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mungai.intothepotter_verse.data.local.entity.CharacterEntity
import com.mungai.intothepotter_verse.data.local.entity.SpellEntity

// This is a Data Access Object (DAO) interface for accessing and manipulating data in the PotterVerse database.
@Dao
interface PotterVerseDao {

    // Inserts a list of CharacterEntity objects into the character_table.
// If there is a conflict, the existing data is replaced with the new data.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharacterEntity>)

    // Returns all CharacterEntity objects from the character_table.
    @Query("SELECT * FROM character_table")
    suspend fun getAllCharacters(): List<CharacterEntity>

    // Returns a CharacterEntity object from the character_table that matches the given characterId.
    @Query("SELECT * FROM CHARACTER_TABLE WHERE id = :characterId")
    suspend fun getCharacterById(characterId: String): CharacterEntity

    // Deletes a CharacterEntity object from the character_table.
    @Delete
    suspend fun deleteCharacter(character: CharacterEntity)

    // Inserts a list of SpellEntity objects into the spell_table.
// If there is a conflict, the existing data is replaced with the new data.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpells(spells: List<SpellEntity>)

    // Returns all SpellEntity objects from the spell_table.
    @Query("SELECT * from spell_table")
    suspend fun getAllSpells(): List<SpellEntity>

    // Returns a SpellEntity object from the spell_table that matches the given id.
    @Query("SELECT * FROM spell_table WHERE id = :id")
    suspend fun getSpellById(id: String): SpellEntity

    // Returns all CharacterEntity objects from the character_table where image is not an empty string.
    @Query("SELECT * FROM character_table WHERE image != ''")
    suspend fun getMainCast(): List<CharacterEntity>

    // Returns all CharacterEntity objects from the character_table where hogwartsStudent is true.
    @Query("SELECT * FROM character_table WHERE hogwartsStudent = 1")
    suspend fun getStudents(): List<CharacterEntity>

    // Returns all CharacterEntity objects from the character_table where hogwartsStaff is true.
    @Query("SELECT * FROM character_table WHERE hogwartsStaff = 1")
    suspend fun getStaff(): List<CharacterEntity>

    // Returns all CharacterEntity objects from the character_table where house matches the given house parameter.
    @Query("SELECT * FROM character_table WHERE house = :house")
    suspend fun getCharactersByHouse(house: String): List<CharacterEntity>

    // Returns all CharacterEntity objects from the character_table where name contains the given name parameter.
    @Query("SELECT * FROM character_table WHERE name LIKE '%' || :name || '%'")
    suspend fun getCharactersByName(name: String): List<CharacterEntity>

    // Returns all CharacterEntity objects from the character_table where house matches the given house parameter
// and name contains the given name parameter.
    @Query("SELECT * FROM character_table WHERE house LIKE '%' || :house || '%' AND name LIKE '%' || :name || '%'")
    suspend fun getCharactersByNameAndHouse(house: String, name: String): List<CharacterEntity>


}