package com.mungai.intothepotter_verse.presentation.search.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mungai.intothepotter_verse.R
import com.mungai.intothepotter_verse.presentation.common.ui.ErrorCard
import com.mungai.intothepotter_verse.presentation.common.ui.Loading
import com.mungai.intothepotter_verse.presentation.search.SearchViewModel
import com.mungai.intothepotter_verse.presentation.search.component.SearchResult
import com.mungai.intothepotter_verse.presentation.search.component.SearchWidget

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController,
    bottomPadding: Dp
) {

    val state = viewModel.state.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Outlined.ArrowBack,
                                contentDescription = "back"
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Search Screen",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            fontFamily = FontFamily(
                                Font(R.font.baskervville_italic)
                            ),
                            color = Color.Black
                        )
                    }
                },
                backgroundColor = Color(0xFFD86767)
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = innerPadding.calculateTopPadding()
                ),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (state.loading) {
                Loading()
            }

            state.error?.let {
                ErrorCard(errorMessage = it)
            }

            SearchWidget(
                modifier = Modifier
                    .padding(vertical = 16.dp),
                updateSearchType = viewModel::updateSearchType,
                isNormalSearch = state.isNormalSearch,
                query = state.query,
                updateQuery = viewModel::updateQuery,
                updateHouseName = viewModel::updateHouseName
            ) {
                viewModel.onSearch()
            }

            Divider(thickness = 3.dp, color = Color(0xFFFFD700))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = bottomPadding),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(state.characters) { character ->
                    SearchResult(character = character) {
                        navController.navigate("character_details_screen" + "?characterId=${it}")
                    }
                }
            }

        }
    }
}