package com.mungai.intothepotter_verse.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mungai.intothepotter_verse.data.local.entity.CharacterEntity
import com.mungai.intothepotter_verse.data.local.entity.WandEntity

@Database(entities = [CharacterEntity::class, WandEntity::class], version = 1, exportSchema = false)
abstract class PotterVerseDatabase : RoomDatabase() {
}