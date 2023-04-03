package com.mungai.intothepotter_verse.presentation.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BabyChangingStation
import androidx.compose.material.icons.outlined.ContentCut
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.RecentActors
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.mungai.intothepotter_verse.R
import com.mungai.intothepotter_verse.presentation.common.Pill
import com.mungai.potterpedia.domain.model.Character

@Composable
fun OtherDetails(
    modifier: Modifier = Modifier,
    character: Character
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Pill(
            icon = Icons.Outlined.Dashboard,
            title = Pair("Other Details", MaterialTheme.colors.onSurface),
            isHeader = true
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            if (character.eyeColour != "") {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Pill(
                        icon = Icons.Outlined.RemoveRedEye,
                        title = Pair("Eye color", MaterialTheme.colors.onSurface),
                        isHeader = true
                    )
                    Text(
                        text = character.eyeColour,
                        fontFamily = FontFamily(Font(R.font.garamond_semibold_italic)),
                    )
                }
            }
            if (character.hairColour != "") {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Pill(
                        icon = Icons.Outlined.ContentCut,
                        title = Pair("Hair color", MaterialTheme.colors.onSurface),
                        isHeader = true
                    )
                    Text(
                        text = character.hairColour,
                        fontFamily = FontFamily(Font(R.font.garamond_semibold_italic)),
                    )
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            character.dateOfBirth?.let { dob ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Pill(
                        icon = Icons.Outlined.Event,
                        title = Pair("Date of Birth", MaterialTheme.colors.onSurface),
                        isHeader = true
                    )
                    Text(
                        text = dob,
                        fontFamily = FontFamily(Font(R.font.garamond_semibold_italic)),
                    )
                }
            }
            character.yearOfBirth?.let { yob ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Pill(
                        icon = Icons.Outlined.BabyChangingStation,
                        title = Pair("Year of Birth", MaterialTheme.colors.onSurface),
                        isHeader = true
                    )
                    Text(
                        text = yob.toString(),
                        fontFamily = FontFamily(Font(R.font.garamond_semibold_italic)),
                    )
                }
            }
        }
        if (character.alternateActors.isNotEmpty()) {
            Pill(
                icon = Icons.Outlined.RecentActors,
                title = Pair("Alternate actors", MaterialTheme.colors.onSurface)
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(character.alternateActors) { actor ->
                    Surface {
                        Text(
                            text = "'$actor'",
                            fontFamily = FontFamily(Font(R.font.garamond_semibold_italic))
                        )
                    }
                }
            }
        }
    }

}