package com.mungai.intothepotter_verse.presentation.detail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Construction
import androidx.compose.material.icons.outlined.KeyboardCommandKey
import androidx.compose.material.icons.outlined.SwitchAccessShortcut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mungai.intothepotter_verse.R
import com.mungai.intothepotter_verse.presentation.common.data.getHouseColors
import com.mungai.intothepotter_verse.presentation.common.ui.Pill
import com.mungai.potterpedia.domain.model.Character

@Composable
fun WandItem(
    modifier: Modifier = Modifier,
    character: Character,
) {
    Surface(
        modifier = modifier
            .width(300.dp)
            .height(150.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 12.dp
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.wand_magic),
                contentDescription = "wand",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1f)
            )
            Text(
                text = "Wand",
                textDecoration = TextDecoration.Underline,
                fontFamily = FontFamily(Font(R.font.baskervville_italic)),
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 12.dp),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .padding(bottom = 12.dp, start = 12.dp, end = 12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (character.wand.core != "") {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Pill(
                            icon = Icons.Outlined.KeyboardCommandKey,
                            title = Pair("Core", getHouseColors(character.house).first),
                            isHeader = true,
                            iconSize = 15.dp,
                            titleSize = 15.sp
                        )
                        Text(
                            text = character.wand.core,
                            fontFamily = FontFamily(Font(R.font.garamond_semibold_italic)),
                            color = Color.White
                        )
                    }
                }
                character.wand.length?.let { length ->
                    Column(
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Pill(
                            icon = Icons.Outlined.SwitchAccessShortcut,
                            title = Pair("Length", getHouseColors(character.house).first),
                            isHeader = true,
                            iconSize = 15.dp,
                            titleSize = 15.sp
                        )
                        Text(
                            text = length.toString(),
                            fontFamily = FontFamily(Font(R.font.garamond_semibold_italic)),
                            color = Color.White
                        )
                    }
                }
                if (character.wand.wood != "") {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Pill(
                            icon = Icons.Outlined.Construction,
                            title = Pair("Wood", getHouseColors(character.house).first),
                            isHeader = true,
                            iconSize = 15.dp,
                            titleSize = 15.sp
                        )
                        Text(
                            text = character.wand.wood,
                            fontFamily = FontFamily(Font(R.font.garamond_semibold_italic)),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

