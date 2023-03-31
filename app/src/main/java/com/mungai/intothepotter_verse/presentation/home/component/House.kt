package com.mungai.intothepotter_verse.presentation.home.component

import androidx.compose.ui.graphics.Color
import com.mungai.intothepotter_verse.R

sealed class House(val name: String, val badge: Int, val houseColor: Color) {

    object Slytherin :
        House(name = "Slytherin", badge = R.drawable.slytherin_logo, houseColor = Color(0xFF006400))

    object Gryffindor : House(
        name = "Gryffindor",
        badge = R.drawable.gryffindor_logo,
        houseColor = Color(0xFF8B0000)
    )

    object RavenClaw :
        House(name = "Ravenclaw", badge = R.drawable.ravenclaw_logo, houseColor = Color(0xFF00008B))

    object Hufflepuff : House(
        name = "Hufflepuff",
        badge = R.drawable.hufflepuff_logo,
        houseColor = Color(0xFFFFDB58)
    )
}
