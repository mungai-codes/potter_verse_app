package com.mungai.intothepotter_verse.presentation.home.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoFixHigh
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Grade
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.SelfImprovement
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mungai.intothepotter_verse.R
import com.mungai.intothepotter_verse.common.Category
import com.mungai.intothepotter_verse.presentation.common.LazyRowItem
import com.mungai.intothepotter_verse.presentation.common.Pill
import com.mungai.intothepotter_verse.presentation.home.HomeViewModel
import com.mungai.intothepotter_verse.presentation.home.component.CharacterCard
import com.mungai.intothepotter_verse.presentation.home.component.Header
import com.mungai.intothepotter_verse.presentation.home.component.SpellCard
import com.mungai.intothepotter_verse.presentation.navigation.bottom_navigation.BottomNavItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
    bottomPadding: Dp
) {


    val state = viewModel.state.collectAsState().value

    val scaffoldState = rememberScaffoldState()

    val scope = rememberCoroutineScope()

    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.Hidden },
        skipHalfExpanded = false
    )

    BackHandler(enabled = modalSheetState.isVisible) {
        scope.launch { modalSheetState.hide() }
    }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(10.dp),
        sheetElevation = 16.dp,
        sheetContent = {
            state.spell?.let { spell ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = spell.name,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.euphoria_script)),
                        modifier = Modifier.padding(top = 12.dp)
                    )
                    Text(
                        text = spell.description,
                        fontFamily = FontFamily(Font(R.font.garamond_semibold_italic)),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                }
            }
        }
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
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
                        .padding(bottom = bottomPadding)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(vertical = 12.dp)
                ) {

                    item {
                        LazyRowItem(data = state.mainCast, content = {
                            Pill(
                                isHeader = true,
                                icon = Icons.Outlined.Grade,
                                title = Pair("Main Cast", MaterialTheme.colors.onSurface)
                            )
                            Pill(
                                icon = Icons.Outlined.ChevronRight,
                                title = Pair("See All", MaterialTheme.colors.onSurface),
                                onClick = {
                                    navController.navigate(BottomNavItem.Search.route + "?category=${Category.Cast.category}")
                                }
                            )
                        }) { character ->
                            CharacterCard(character = character) {
                                navController.navigate("character_details_screen" + "?characterId=${it}")
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
                                    title = Pair("Staff", MaterialTheme.colors.onSurface)
                                )
                            }
                        ) { character ->
                            CharacterCard(character = character) {
                                navController.navigate("character_details_screen" + "?characterId=${it}")
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
                                    title = Pair("Spells", MaterialTheme.colors.onSurface)
                                )
                                Pill(
                                    icon = Icons.Outlined.ChevronRight,
                                    title = Pair("See More", MaterialTheme.colors.onSurface),
                                    onClick = { }
                                )
                            }
                        ) { spell ->
                            SpellCard(spell = spell) { id ->
                                scope.launch {
                                    viewModel.getSpellById(id)
                                    modalSheetState.show()
                                }
                            }
                        }
                    }

                    item {
                        LazyRowItem(
                            data = state.wizards,
                            content = {
                                Pill(
                                    isHeader = true,
                                    icon = Icons.Outlined.SelfImprovement,
                                    title = Pair("Wizards", MaterialTheme.colors.onSurface)
                                )
                                Pill(
                                    icon = Icons.Outlined.ChevronRight,
                                    title = Pair("See More", MaterialTheme.colors.onSurface),
                                    onClick = {

                                    }
                                )
                            }
                        ) { character ->
                            CharacterCard(character = character) {
                                navController.navigate("character_details_screen" + "?characterId=${it}")
                            }
                        }
                    }

                    item {
                        LazyRowItem(
                            data = state.students,
                            content = {
                                Pill(
                                    isHeader = true,
                                    icon = Icons.Outlined.AutoStories,
                                    title = Pair("Students", MaterialTheme.colors.onSurface)
                                )
                            }
                        ) { character ->
                            CharacterCard(character = character) {
                                navController.navigate("character_details_screen" + "?characterId=${it}")
                            }
                        }
                    }


                }
            }
        }

    }
}