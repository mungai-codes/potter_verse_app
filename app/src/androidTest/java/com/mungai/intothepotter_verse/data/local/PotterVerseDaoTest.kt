package com.mungai.intothepotter_verse.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.mungai.intothepotter_verse.data.local.entity.CharacterEntity
import com.mungai.intothepotter_verse.data.local.entity.SpellEntity
import com.mungai.intothepotter_verse.data.local.entity.WandData
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class PotterVerseDaoTest {

    private lateinit var database: PotterVerseDatabase
    private lateinit var dao: PotterVerseDao

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PotterVerseDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.dao
    }

    @Test
    fun characters_are_inserted_to_database() = runBlocking {
        val wand = WandData(
            core = "Unicorn tail hair",
            length = 13.5,
            wood = "Holly"
        )

        val wand1 = WandData(
            core = "Dragon heartstring",
            length = 12.0,
            wood = "Maple"
        )

        val characters = listOf(
            CharacterEntity(
                actor = "Daniel Radcliffe",
                alive = true,
                alternateActors = listOf("Harry Melling", "Benjamin Evan Ainsworth"),
                alternateNames = listOf("The Boy Who Lived", "Undesirable No. 1"),
                ancestry = "Half-blood",
                dateOfBirth = "31 July 1980",
                eyeColour = "Green",
                gender = "Male",
                hairColour = "Black",
                hogwartsStaff = false,
                hogwartsStudent = true,
                house = "Gryffindor",
                id = "5a0fa4daae5bc100213c232e",
                image = "http://hp-api.herokuapp.com/images/harry.jpg",
                name = "Harry Potter",
                patronus = "Stag",
                species = "Human",
                wand = wand,
                wizard = true,
                yearOfBirth = 1980
            ),
            CharacterEntity(
                actor = "Emma Watson",
                alive = true,
                alternateActors = listOf("Sophie Okenedo", "Nina Young"),
                alternateNames = listOf("Hermy", "Head Girl"),
                ancestry = "Muggle-born",
                dateOfBirth = "15 April 1979",
                eyeColour = "Brown",
                gender = "Female",
                hairColour = "Brown",
                hogwartsStaff = false,
                hogwartsStudent = true,
                house = "Gryffindor",
                id = "5a0fa7dcae5bc100213c2331",
                image = "http://hp-api.herokuapp.com/images/hermione.jpeg",
                name = "Hermione Granger",
                patronus = "Otter",
                species = "Human",
                wand = wand1,
                wizard = true,
                yearOfBirth = 1979
            )
        )

        dao.insertCharacters(characters)

        val data = dao.getAllCharacters()

        Assert.assertNotNull(data)

        // Checking both all characters are inserted
        Assert.assertEquals(characters.size, data.size)

    }

    @Test
    fun characters_can_be_retrieved_by_their_id() = runBlocking {
        val wand = WandData(
            core = "Unicorn tail hair",
            length = 13.5,
            wood = "Holly"
        )

        val wand1 = WandData(
            core = "Dragon heartstring",
            length = 12.0,
            wood = "Maple"
        )

        val characters = listOf(
            CharacterEntity(
                actor = "Daniel Radcliffe",
                alive = true,
                alternateActors = listOf("Harry Melling", "Benjamin Evan Ainsworth"),
                alternateNames = listOf("The Boy Who Lived", "Undesirable No. 1"),
                ancestry = "Half-blood",
                dateOfBirth = "31 July 1980",
                eyeColour = "Green",
                gender = "Male",
                hairColour = "Black",
                hogwartsStaff = false,
                hogwartsStudent = true,
                house = "Gryffindor",
                id = "5a0fa4daae5bc100213c232e",
                image = "http://hp-api.herokuapp.com/images/harry.jpg",
                name = "Harry Potter",
                patronus = "Stag",
                species = "Human",
                wand = wand,
                wizard = true,
                yearOfBirth = 1980
            ),
            CharacterEntity(
                actor = "Emma Watson",
                alive = true,
                alternateActors = listOf("Sophie Okenedo", "Nina Young"),
                alternateNames = listOf("Hermy", "Head Girl"),
                ancestry = "Muggle-born",
                dateOfBirth = "15 April 1979",
                eyeColour = "Brown",
                gender = "Female",
                hairColour = "Brown",
                hogwartsStaff = false,
                hogwartsStudent = true,
                house = "Gryffindor",
                id = "5a0fa7dcae5bc100213c2331",
                image = "http://hp-api.herokuapp.com/images/hermione.jpeg",
                name = "Hermione Granger",
                patronus = "Otter",
                species = "Human",
                wand = wand1,
                wizard = true,
                yearOfBirth = 1979
            )
        )

        dao.insertCharacters(characters)

        val data = dao.getAllCharacters()

        Assert.assertNotNull(data)

        val character = dao.getCharacterById("5a0fa4daae5bc100213c232e")

        Assert.assertNotNull(character)

        val expectedCharacter = characters[0]

        Assert.assertEquals(expectedCharacter, character)
    }

    @Test
    fun spells_are_inserted_to_the_database() = runBlocking {
        val spell1 = SpellEntity(
            description = "Causes the target to inflate like a balloon",
            id = "spell001",
            name = "Engorgio"
        )

        val spell2 = SpellEntity(
            description = "Unlocks doors",
            id = "spell002",
            name = "Alohomora"
        )

        val spells = listOf(spell1, spell2)

        dao.insertSpells(spells)

        val response = dao.getAllSpells()

        Assert.assertNotNull(response)

        // Checking both all spells are inserted
        Assert.assertEquals(spells.size, response.size)
    }

    @Test
    fun spells_can_be_retrieved_by_their_id() = runBlocking {
        val spell = SpellEntity(
            description = "Causes the target to inflate like a balloon",
            id = "spell001",
            name = "Engorgio"
        )

        val spell1 = SpellEntity(
            description = "Unlocks doors",
            id = "spell002",
            name = "Alohomora"
        )
        val spells = listOf(spell, spell1)

        dao.insertSpells(spells)

        val response = dao.getAllSpells()

        Assert.assertNotNull(response)

        // Checking both all spells are inserted
        Assert.assertEquals(spells.size, response.size)

        val expectedSpell = dao.getSpellById("spell001")

        Assert.assertEquals(expectedSpell, spells[0])
    }

    @After
    fun closeDatabase() {
        database.close()
    }
}