package com.mungai.intothepotter_verse.presentation.home.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mungai.intothepotter_verse.R
import com.mungai.intothepotter_verse.common.getHouseColors
import com.mungai.potterpedia.domain.model.Character

@Composable
fun CharacterCard(
    modifier: Modifier = Modifier,
    character: Character,
    imageSize: Dp = 100.dp,
    textSize: TextUnit = 15.sp,
    onClick: (String) -> Unit = {}
) {

    val errorImage = if (character.hogwartsStudent) R.drawable.wizard_junior else R.drawable.wizard
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Surface(
            modifier = Modifier
                .size(imageSize),
            shape = CircleShape,
            border = BorderStroke(width = 3.dp, color = getHouseColors(character.house).second),
            elevation = 12.dp
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data = character.image)
                    .placeholder(R.drawable.loading_animation)
                    .error(errorImage)
                    .build(),
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onClick(character.id) }
                    .aspectRatio(1f)
            )
        }
        Text(
            text = character.name,
            fontFamily = FontFamily(Font(R.font.euphoria_script)),
            fontWeight = FontWeight.Bold,
            fontSize = textSize,
            textAlign = TextAlign.Center,
            color = getHouseColors(character.house).third
        )
    }


}