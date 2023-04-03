package com.mungai.intothepotter_verse.presentation.search

import com.mungai.intothepotter_verse.common.Category
import com.mungai.potterpedia.domain.model.Character

data class SearchScreenState(
    val characters: List<Character> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null,
    val category: String = Category.Cast.category
)
