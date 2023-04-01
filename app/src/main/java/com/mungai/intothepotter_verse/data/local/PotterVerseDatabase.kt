package com.mungai.intothepotter_verse.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mungai.intothepotter_verse.data.local.entity.CharacterEntity
import com.mungai.intothepotter_verse.data.local.entity.SpellEntity

@Database(
    entities = [CharacterEntity::class, SpellEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class PotterVerseDatabase : RoomDatabase() {

    abstract val dao: PotterVerseDao
}