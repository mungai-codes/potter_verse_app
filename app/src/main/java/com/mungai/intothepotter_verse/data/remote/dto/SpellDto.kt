package com.mungai.intothepotter_verse.data.remote.dto

import com.mungai.intothepotter_verse.data.local.entity.SpellEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpellDto(
    val description: String,
    val id: String,
    val name: String
) {

    fun toSpellEntity() = SpellEntity(
        description = description,
        id = id,
        name = name
    )

}