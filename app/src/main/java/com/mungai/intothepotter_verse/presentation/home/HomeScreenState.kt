package com.mungai.intothepotter_verse.presentation.home

import com.mungai.intothepotter_verse.domain.model.Spell
import com.mungai.potterpedia.domain.model.Character

data class HomeScreenState(
    val characters: List<Character> = emptyList(),
    val spells: List<Spell> = emptyList(),
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val query: String = ""
)
