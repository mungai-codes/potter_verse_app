package com.mungai.intothepotter_verse.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun fromListToString(list: List<String?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringToList(value: String?): List<String> {
        val listType = object :
            TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }
}

