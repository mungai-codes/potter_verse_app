package com.mungai.intothepotter_verse.presentation.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mungai.intothepotter_verse.R
import com.mungai.intothepotter_verse.presentation.home.component.House

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HouseOption(
    modifier: Modifier = Modifier,
    onHouseSelected: (String) -> Unit
) {

    val houses = listOf(
        House.Gryffindor.name,
        House.Slytherin.name,
        House.RavenClaw.name,
        House.Hufflepuff.name
    )

    var selectedItem by remember {
        mutableStateOf(houses[0])
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    Box(modifier = modifier.width(120.dp)) {

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {

            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                elevation = 12.dp
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = selectedItem,
                        fontFamily = FontFamily(Font(R.font.garamond_semibold_italic)),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(5.dp),
                        color = MaterialTheme.colors.onSurface
                    )
                    IconButton(
                        onClick = { }
                    ) {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded,
                        )
                    }
                }
            }

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(MaterialTheme.colors.background),
            ) {
                houses.forEach { selectedOption ->

                    DropdownMenuItem(onClick = {
                        onHouseSelected(selectedOption)
                        selectedItem = selectedOption
                        expanded = false
                    }) {
                        Text(
                            text = selectedOption,
                            fontFamily = FontFamily(Font(R.font.garamond_semibold_italic)),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                }
            }
        }
    }
}
