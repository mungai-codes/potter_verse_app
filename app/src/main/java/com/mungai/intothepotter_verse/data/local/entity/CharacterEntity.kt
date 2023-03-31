package com.mungai.intothepotter_verse.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mungai.potterpedia.domain.model.Character

@Entity(tableName = "character_table")
data class CharacterEntity(
    val actor: String,
    val alive: Boolean,
    val alternateActors: List<String>,
    val alternateNames: List<String>,
    val ancestry: String,
    val dateOfBirth: String?,
    val eyeColour: String,
    val gender: String,
    val hairColour: String,
    val hogwartsStaff: Boolean,
    val hogwartsStudent: Boolean,
    val house: String,
    @PrimaryKey
    val id: String,
    val image: String?,
    val name: String,
    val patronus: String,
    val species: String,
    @Embedded
    val wand: WandData,
    val wizard: Boolean,
    val yearOfBirth: Int?
) {
    fun toCharacter() = Character(
        actor = actor,
        alive = alive,
        alternateActors = alternateActors,
        alternateNames = alternateNames,
        ancestry = ancestry,
        dateOfBirth = dateOfBirth,
        eyeColour = eyeColour,
        gender = gender,
        hairColour = hairColour,
        hogwartsStaff = hogwartsStaff,
        hogwartsStudent = hogwartsStudent,
        house = house,
        id = id,
        image = image,
        name = name,
        patronus = patronus,
        species = species,
        wand = wand.toWand(),
        wizard = wizard,
        yearOfBirth = yearOfBirth
    )
}