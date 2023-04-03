package com.mungai.intothepotter_verse.common

sealed class Category(val category: String) {

    object Cast : Category(category = "cast")

    object Spell : Category(category = "spell")

    object Wizard : Category(category = "wizard")
}
