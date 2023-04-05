package com.mungai.intothepotter_verse.presentation.detail.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mungai.intothepotter_verse.R
import com.mungai.intothepotter_verse.presentation.common.data.getHouseColors
import com.mungai.intothepotter_verse.presentation.detail.DetailsViewModel
import com.mungai.intothepotter_verse.presentation.detail.component.DetailBody
import com.mungai.intothepotter_verse.presentation.detail.component.DetailHeader
import com.mungai.intothepotter_verse.presentation.detail.component.OtherDetails


@Composable
fun CharacterDetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel(),
    navController: NavController
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
                        state.character?.let { character ->
                            Text(
                                text = "Character Details",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                fontFamily = FontFamily(
                                    Font(R.font.baskervville_italic)
                                ),
                                color = getHouseColors(character.house).second
                            )
                        }
                    }
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

            if (state.loading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            state.error?.let {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = it)
                }
            }

            state.character?.let { character ->
                LazyColumn(
                    modifier = Modifier,
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        DetailHeader(character = character)
                    }

                    item {
                        DetailBody(character = character)
                    }

                    item {
                        OtherDetails(character = character)
                    }
                }
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun DetailsScreenPreveiw() {
//    IntoThePotterVerseTheme(darkTheme = true) {
//        CharacterDetailsScreen()
//    }
//}
