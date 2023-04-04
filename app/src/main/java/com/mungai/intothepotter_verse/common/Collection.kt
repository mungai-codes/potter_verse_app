package com.mungai.intothepotter_verse.common

sealed class Collection(val name: String) {

    object Cast : Collection(name = "cast")

    object Spell : Collection(name = "spell")

    object House : Collection(name = "house")
}
