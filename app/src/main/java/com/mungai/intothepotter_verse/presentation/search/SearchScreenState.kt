package com.mungai.intothepotter_verse.presentation.search

import com.mungai.intothepotter_verse.common.Collection
import com.mungai.intothepotter_verse.domain.model.Spell
import com.mungai.potterpedia.domain.model.Character

data class SearchScreenState(
    val characters: List<Character> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null,
)
