package com.mungai.intothepotter_verse.presentation.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoFixHigh
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Grade
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mungai.intothepotter_verse.R
import com.mungai.intothepotter_verse.presentation.common.LazyRowItem
import com.mungai.intothepotter_verse.presentation.common.Pill
import com.mungai.intothepotter_verse.presentation.home.HomeViewModel
import com.mungai.intothepotter_verse.presentation.home.component.CharacterCard
import com.mungai.intothepotter_verse.presentation.home.component.Header
import com.mungai.intothepotter_verse.presentation.home.component.SpellCard

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
    bottomPadding: Dp
) {


    val state = viewModel.state.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Potter Pedia",
                        fontFamily = FontFamily(Font(R.font.euphoria_script))
                    )
                },
                backgroundColor = Color(0xFFD86767)
            )

        }
    ) { innerPadding ->

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
        ) {

            Header(
                query = state.query,
                onQueryChange = viewModel::updateQuery,
                onSearch = {

                },
                onHouseClick = {

                }
            )

            LazyColumn(
                modifier = Modifier
                    .padding(
                        bottom = bottomPadding
                    ),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {

                item {
                    LazyRowItem(data = state.mainCast, content = {
                        Pill(
                            isHeader = true,
                            icon = Icons.Outlined.Grade,
                            title = "Main Cast"
                        )
                    }) { character ->
                        CharacterCard(character = character) {

                        }
                    }
                }

                item {
                    LazyRowItem(
                        data = state.staff,
                        content = {
                            Pill(
                                isHeader = true,
                                icon = Icons.Outlined.School,
                                title = "Staff"
                            )
                        }
                    ) { character ->
                        CharacterCard(character = character) {

                        }
                    }
                }
                item {
                    LazyRowItem(
                        data = state.spells,
                        content = {
                            Pill(
                                isHeader = true,
                                icon = Icons.Outlined.AutoFixHigh,
                                title = "Spells"
                            )
                            Pill(
                                icon = Icons.Outlined.ChevronRight,
                                title = "See More",
                                onClick = { }
                            )
                        }
                    ) { spell ->
                        SpellCard(spell = spell) {

                        }
                    }
                }
            }
        }
    }
}