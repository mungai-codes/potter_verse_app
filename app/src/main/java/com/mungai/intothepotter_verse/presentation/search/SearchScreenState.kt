package com.mungai.intothepotter_verse.presentation.search

import com.mungai.potterpedia.domain.model.Character

data class SearchScreenState(
    val characters: List<Character> = emptyList(),
    val query: String = "",
    val house: String = "",
    val loading: Boolean = false,
    val error: String? = null,
    val isNormalSearch: Boolean = true
)
