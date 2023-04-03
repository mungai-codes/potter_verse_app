package com.mungai.intothepotter_verse.presentation.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mungai.intothepotter_verse.R
import com.mungai.intothepotter_verse.domain.model.Spell

@Composable
fun SpellCard(
    modifier: Modifier = Modifier,
    spell: Spell,
    onClick: (String) -> Unit = {}
) {
    Surface(
        modifier = modifier
            .width(100.dp)
            .height(110.dp)
            .clickable { onClick(spell.name) },
        shape = RoundedCornerShape(16.dp),
        elevation = 12.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = spell.name,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(R.font.garamond_semibold_italic)),
                fontSize = 15.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = spell.description,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.euphoria_script)),
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }

}