package com.mungai.intothepotter_verse.presentation.collection

import com.mungai.intothepotter_verse.domain.model.Spell
import com.mungai.potterpedia.domain.model.Character

data class CollectionScreenState(
    val characters: List<Character> = emptyList(),
    val spells: List<Spell> = emptyList(),
    val title: String = "",
    val house: String = "",
    val error: String? = null,
    val loading: Boolean = false,
    val showSpells: Boolean = false
)