package com.mungai.intothepotter_verse.presentation.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Extension
import androidx.compose.material.icons.outlined.MovieCreation
import androidx.compose.material.icons.outlined.Spa
import androidx.compose.material.icons.outlined.Timeline
import androidx.compose.material.icons.outlined.Transgender
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.mungai.intothepotter_verse.R
import com.mungai.intothepotter_verse.presentation.common.ui.Pill
import com.mungai.potterpedia.domain.model.Character

@Composable
fun DetailBody(
    modifier: Modifier = Modifier,
    character: Character
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (character.alternateNames.isNotEmpty()) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(character.alternateNames) {
                    Surface {
                        Text(
                            text = "'$it'",
                            fontFamily = FontFamily(Font(R.font.garamond_semibold_italic))
                        )
                    }
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Pill(
                icon = Icons.Outlined.Timeline,
                title = Pair("Ancestry", MaterialTheme.colors.onSurface),
                isHeader = true
            )
            Text(
                text = if (character.ancestry == "") "N/A" else character.ancestry,
                fontFamily = FontFamily(Font(R.font.garamond_semibold_italic)),
            )
        }
        if (character.patronus != "") {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Pill(
                    icon = Icons.Outlined.Spa,
                    title = Pair("Patronus", MaterialTheme.colors.onSurface),
                    isHeader = true
                )
                Text(
                    text = character.patronus,
                    fontFamily = FontFamily(Font(R.font.garamond_semibold_italic)),
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Pill(
                icon = Icons.Outlined.Category,
                title = Pair("Species", MaterialTheme.colors.onSurface),
                isHeader = true
            )
            Text(
                text = character.species,
                fontFamily = FontFamily(Font(R.font.garamond_semibold_italic)),
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Pill(
                icon = Icons.Outlined.Extension,
                title = Pair("Role", MaterialTheme.colors.onSurface),
                isHeader = true
            )

            Text(
                text = if (character.hogwartsStudent) "student" else if (character.hogwartsStaff) "staff" else "supporting cast",
                fontFamily = FontFamily(Font(R.font.garamond_semibold_italic))
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Pill(
                icon = Icons.Outlined.Transgender,
                title = Pair("Gender", MaterialTheme.colors.onSurface),
                isHeader = true
            )
            Text(
                text = character.gender,
                fontFamily = FontFamily(Font(R.font.garamond_semibold_italic))
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Pill(
                icon = Icons.Outlined.MovieCreation,
                title = Pair(
                    "Actor",
                    MaterialTheme.colors.onSurface
                ), isHeader = true
            )

            Text(
                text = character.actor,
                fontFamily = FontFamily(Font(R.font.garamond_semibold_italic))
            )
        }

        if (character.wand.core != "" || character.wand.length != null || character.wand.wood != "") {
            WandItem(character = character)
        }
    }
}