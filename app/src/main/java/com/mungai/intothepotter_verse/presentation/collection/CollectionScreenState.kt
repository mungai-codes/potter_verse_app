package com.mungai.intothepotter_verse.presentation.collection

import com.mungai.intothepotter_verse.common.Collection
import com.mungai.intothepotter_verse.domain.model.Spell
import com.mungai.potterpedia.domain.model.Character

data class CollectionScreenState(
    val characters: List<Character> = emptyList(),
    val spells: List<Spell> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null,
    val house: String = "",
    val collection: String = "Collection.Cast.name",
    val showSpells: Boolean = false
)