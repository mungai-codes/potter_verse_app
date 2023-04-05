package com.mungai.potterpedia.data.remote.dto

import com.mungai.intothepotter_verse.data.local.entity.CharacterEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterDto(
    val actor: String,
    val alive: Boolean,
    @Json(name = "alternate_actors")
    val alternateActors: List<String>,
    @Json(name = "alternate_names")
    val alternateNames: List<String>,
    val ancestry: String,
    val dateOfBirth: String?,
    val eyeColour: String,
    val gender: String,
    val hairColour: String,
    val hogwartsStaff: Boolean,
    val hogwartsStudent: Boolean,
    val house: String,
    val id: String,
    val image: String?,
    val name: String,
    val patronus: String,
    val species: String,
    val wand: WandDto,
    val wizard: Boolean,
    val yearOfBirth: Int?
) {
    fun toCharacterEntity() = CharacterEntity(
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
        wand = wand.toWandEntity(),
        wizard = wizard,
        yearOfBirth = yearOfBirth
    )
}