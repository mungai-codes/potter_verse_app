package com.mungai.intothepotter_verse.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mungai.potterpedia.domain.model.Wand

@Entity(tableName = "wand_table")
data class WandEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
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