package com.mungai.intothepotter_verse.presentation.common.data

import androidx.compose.ui.graphics.Color
import com.mungai.intothepotter_verse.R

fun getHouseColors(house: String): Triple<Color, Color, Color> {
    return when (house) {
        "Gryffindor" -> {
            Triple(Color(0xff800000), Color(0xFFFFD700), Color(0xFF8B0000))
        }

        "Slytherin" -> {
            Triple(Color(0xFF008000), Color(0xFFC0C0C0), Color(0xFF006400))
        }

        "Ravenclaw" -> {
            Triple(Color(0xFF0000FF), Color(0xFFCD7F32), Color(0xFF00008B))
        }

        "Hufflepuff" -> {
            Triple(Color(0xFFFFFF00), Color(0xFF000000), Color(0xFFFFDB58))
        }

        else -> {
            Triple(Color(0xFFA52A2A), Color(0xFFFFD700), Color(0xFF000000))
        }
    }
}

fun getHouseBadge(house: String): Int {
    return when (house) {
        "Gryffindor" -> {
            R.drawable.gryffindor_logo
        }

        "Slytherin" -> {
            R.drawable.slytherin_logo
        }

        "Ravenclaw" -> {
            R.drawable.ravenclaw_logo
        }

        "Hufflepuff" -> {
            R.drawable.hufflepuff_logo
        }

        else -> {
            R.drawable.hogwarts_logo
        }
    }
}
