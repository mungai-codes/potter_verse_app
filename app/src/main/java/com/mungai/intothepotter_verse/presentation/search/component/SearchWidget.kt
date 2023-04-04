package com.mungai.intothepotter_verse.presentation.search.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.mungai.intothepotter_verse.R

@Composable
fun SearchWidget(
    modifier: Modifier = Modifier,
    updateSearchType: (Boolean) -> Unit,
    isNormalSearch: Boolean,
    query: String,
    updateQuery: (String) -> Unit,
    updateHouseName: (String) -> Unit = {},
    onSearch: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .animateContentSize(
                animationSpec = spring(Spring.DampingRatioMediumBouncy, Spring.StiffnessMedium)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        if (isNormalSearch) {
            OutlinedTextField(
                value = query,
                onValueChange = updateQuery,
                shape = RoundedCornerShape(16.dp),
                trailingIcon = {
                    IconButton(onClick = { onSearch() }) {
                        Icon(imageVector = Icons.Outlined.Search, contentDescription = "search")
                    }
                },
                placeholder = {
                    Text(
                        text = "Normal search...",
                        fontFamily = FontFamily(Font(R.font.garamond_semibold_italic))
                    )
                },
                textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.garamond_semibold_italic))),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color(0xFFA52A2A),
                    focusedBorderColor = Color(0xFFFFD700),
                    textColor = Color(0xFFA52A2A)
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearch()
                        focusManager.clearFocus(true)
                    },
                    onDone = {
                        focusManager.clearFocus(true)
                    }
                )
            )
            SearchOptions(isNormalSearch = updateSearchType)
        } else {
            HouseOption(onHouseSelected = updateHouseName)
            OutlinedTextField(
                value = query,
                shape = RoundedCornerShape(16.dp),
                onValueChange = updateQuery,
                trailingIcon = {
                    IconButton(onClick = { onSearch() }) {
                        Icon(imageVector = Icons.Outlined.Search, contentDescription = "search")
                    }
                },
                placeholder = {
                    Text(
                        text = "Dual search...",
                        fontFamily = FontFamily(Font(R.font.garamond_semibold_italic))
                    )
                },
                textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.garamond_semibold_italic))),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color(0xFFA52A2A),
                    focusedBorderColor = Color(0xFFFFD700),
                    textColor = Color(0xFFA52A2A)
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearch()
                        focusManager.clearFocus(true)
                    },
                    onDone = {
                        focusManager.clearFocus(true)
                    }
                ),
                modifier = Modifier.defaultMinSize(minWidth = 165.dp)
            )
            SearchOptions(isNormalSearch = updateSearchType)
        }
    }
}