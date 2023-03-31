package com.mungai.intothepotter_verse.data.local.entity

import com.mungai.potterpedia.domain.model.Wand

data class WandData(
    val core: String,
    val length: Double?,
    val wood: String
) {
    fun toWand() = Wand(
        core = core,
        length = length,
        wood = wood
    )
}