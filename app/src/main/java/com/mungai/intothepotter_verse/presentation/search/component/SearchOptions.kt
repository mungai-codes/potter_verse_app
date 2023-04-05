package com.mungai.intothepotter_verse.presentation.search.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.mungai.intothepotter_verse.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchOptions(
    modifier: Modifier = Modifier,
    isNormalSearch: (Boolean) -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Box(modifier = modifier) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = Icons.Outlined.MoreVert,
                contentDescription = "More"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(onClick = { isNormalSearch(true) }) {
                Text(
                    text = "ByName", fontFamily = FontFamily(Font(R.font.garamond_semibold_italic)),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                )
            }

            DropdownMenuItem(onClick = { isNormalSearch(false) }) {
                Text(
                    text = "Dual",
                    fontFamily = FontFamily(Font(R.font.garamond_semibold_italic)),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                )
            }

        }
    }
}
