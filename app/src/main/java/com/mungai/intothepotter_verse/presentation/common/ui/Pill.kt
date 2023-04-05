package com.mungai.intothepotter_verse.presentation.common.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Grade
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mungai.intothepotter_verse.R

@Composable
fun Pill(
    modifier: Modifier = Modifier,
    isHeader: Boolean = false,
    icon: ImageVector,
    iconSize: Dp = 24.dp,
    title: Pair<String, Color>,
    titleSize: TextUnit = 20.sp,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier
            .wrapContentSize(),
        shape = RoundedCornerShape(16.dp),
        color = Color.Transparent
    ) {
        if (isHeader) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title.first,
                    tint = Color(0xFFA52A2A),
                    modifier = Modifier.size(iconSize)
                )
                Text(
                    text = title.first,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.euphoria_script)),
                    fontSize = titleSize,
                    color = title.second
                )
            }
        } else {
            Row(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .clickable { onClick() },
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title.first,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.euphoria_script)),
                    fontSize = titleSize,
                    color = MaterialTheme.colors.onSurface
                )
                Icon(
                    imageVector = icon,
                    contentDescription = title.first,
                    tint = MaterialTheme.colors.onSurface,
                    modifier = Modifier.size(iconSize)
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PillPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Pill(
                isHeader = true,
                icon = Icons.Outlined.Grade,
                title = Pair("Main Cast", MaterialTheme.colors.onSurface)
            )
            Pill(
                isHeader = false,
                icon = Icons.Outlined.ChevronRight,
                title = Pair("SeeMore", MaterialTheme.colors.onSurface)
            )
        }

    }
}