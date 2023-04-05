package com.mungai.intothepotter_verse.presentation.search.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mungai.intothepotter_verse.R
import com.mungai.intothepotter_verse.presentation.common.data.getHouseColors
import com.mungai.potterpedia.domain.model.Character

@Composable
fun SearchResult(
    modifier: Modifier = Modifier,
    character: Character,
    onClick: (String) -> Unit = {}
) {
    val errorImage = if (character.hogwartsStudent) R.drawable.wizard_junior else R.drawable.wizard

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(width = 3.dp, color = getHouseColors(character.house).second),
        elevation = 12.dp
    ) {
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.weight(0.7f)) {
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
                fontFamily = FontFamily(Font(R.font.garamond_semibold_italic)),
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier.weight(0.3f),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onSurface
            )
        }
    }

}