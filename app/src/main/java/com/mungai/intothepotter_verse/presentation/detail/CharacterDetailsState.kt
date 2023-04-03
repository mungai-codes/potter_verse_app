package com.mungai.intothepotter_verse.presentation.detail

import com.mungai.potterpedia.domain.model.Character

data class CharacterDetailsState(
    val character: Character? = null,
    val loading: Boolean = false,
    val error: String? = null
)
