package com.mungai.intothepotter_verse.presentation.home.component

import com.mungai.intothepotter_verse.R

sealed class House(val name: String, val badge: Int) {

    object Slytherin : House(name = "Slytherin", badge = R.drawable.wizard_junior)

    object Gryffindor : House(name = "Gryffindor", badge = R.drawable.wizard_junior)
}
