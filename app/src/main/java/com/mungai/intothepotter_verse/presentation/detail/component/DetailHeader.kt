package com.mungai.intothepotter_verse.presentation.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MonitorHeart
import androidx.compose.material.icons.outlined.SelfImprovement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mungai.intothepotter_verse.common.getHouseBadge
import com.mungai.intothepotter_verse.common.getHouseColors
import com.mungai.intothepotter_verse.presentation.home.component.CharacterCard
import com.mungai.intothepotter_verse.presentation.home.component.HouseBadge
import com.mungai.potterpedia.domain.model.Character

@Composable
fun DetailHeader(
    modifier: Modifier = Modifier,
    character: Character
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            CharacterCard(character = character, imageSize = 150.dp, textSize = 25.sp)
            HouseBadge(
                badge = getHouseBadge(character.house),
                title = character.house,
                textColor = getHouseColors(character.house).third,
                modifier = Modifier.align(Alignment.TopEnd)
            )
            Icon(
                imageVector = Icons.Outlined.MonitorHeart,
                contentDescription = "status",
                tint = if (character.alive) Color.Green else Color.Red,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 10.dp)
            )
            if (character.wizard) {
                Icon(
                    imageVector = Icons.Outlined.SelfImprovement,
                    contentDescription = "wizard",
                    tint = getHouseColors(character.house).second,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 5.dp)
                )
            }
        }
    }
}