package com.mungai.potterpedia.data.remote.dto

import com.mungai.intothepotter_verse.data.local.entity.WandEntity
import com.mungai.potterpedia.domain.model.Wand
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WandDto(
    val core: String,
    val length: Double?,
    val wood: String
) {
    fun toWandEntity() = WandEntity(
        core = core,
        length = length,
        wood = wood
    )
}