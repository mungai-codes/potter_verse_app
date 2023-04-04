package com.mungai.intothepotter_verse.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mungai.intothepotter_verse.R
import com.mungai.intothepotter_verse.presentation.ui.theme.IntoThePotterVerseTheme


@Composable
fun HouseBadge(
    modifier: Modifier = Modifier,
    badge: Int,
    title: String,
    textColor: Color,
    badgeSize: Dp = 65.dp,
    textSize: TextUnit = 14.sp,
    onClick: (String) -> Unit = {}
) {
    Column(
        modifier = modifier
            .wrapContentSize()
            .background(Color.Transparent),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(badgeSize)
                .background(Color.Transparent)
        ) {
            Image(
                painter = painterResource(id = badge),
                contentDescription = title,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1f)
                    .clickable { onClick(title) }
            )
        }
        if (title == "") {
            Text(
                text = "Hogwarts",
                textAlign = TextAlign.Center,
                fontSize = textSize,
                fontFamily = FontFamily(Font(R.font.garamond_semibold_italic)),
                color = textColor
            )
        } else {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                fontSize = textSize,
                fontFamily = FontFamily(Font(R.font.garamond_semibold_italic)),
                color = textColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HouseBadgePreview() {
    IntoThePotterVerseTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HouseBadge(
                badge = R.drawable.slytherin_logo,
                title = "Slytherin",
                textColor = Color(0xFF006400)
            )
        }
    }
}