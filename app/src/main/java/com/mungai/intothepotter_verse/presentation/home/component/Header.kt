package com.mungai.intothepotter_verse.presentation.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Header(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onHouseClick: (String) -> Unit
) {

    val houses = listOf(
        House.Gryffindor,
        House.Slytherin,
        House.RavenClaw,
        House.Hufflepuff
    )

    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        OutlinedTextField(
            value = query,
            onValueChange = { onQueryChange(it) },
            shape = RoundedCornerShape(16.dp),
            placeholder = {
                Text(text = "Search...")
            },
            trailingIcon = {
                IconButton(onClick = { onSearch(query) }) {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = "search"
                    )
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color(0xFF000000),
                focusedBorderColor = Color(0xFFFFD700),
                textColor = Color(0xFFA52A2A)
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch(query)
                    focusManager.clearFocus(true)
                },
                onDone = {
                    focusManager.clearFocus(true)
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 8.dp)
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(houses) { house ->
                HouseBadge(
                    badge = house.badge,
                    title = house.name,
                    textColor = house.houseColor,
                    onClick = { onHouseClick(it) })
            }

        }
        Divider(thickness = 3.dp, color = Color(0xFFFFD700))
    }

}