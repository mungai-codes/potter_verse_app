package com.mungai.intothepotter_verse.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mungai.intothepotter_verse.data.local.entity.CharacterEntity

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

}