package com.mungai.intothepotter_verse.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mungai.intothepotter_verse.domain.model.Spell

@Entity(tableName = "spell_table")
data class SpellEntity(
    val description: String,
    @PrimaryKey
    val id: String,
    val name: String
) {
    fun toSpell() = Spell(
        description = description,
        id = id,
        name = name
    )
}