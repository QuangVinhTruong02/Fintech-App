package com.example.fintechapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fintechapp.common.AppImage

@Composable
fun AvatarUser(
    onClick: (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .size(40.dp)

    ) {
        Image(
            painter = painterResource(AppImage.imgAvatar),
            contentDescription = "User Avatar",
            modifier = Modifier
                .fillMaxSize().clip(CircleShape)
                .clickable { onClick?.invoke() }
                .clip(CircleShape),
            contentScale = ContentScale.Fit
        )
        Box(
            modifier = Modifier
                .size(10.dp)
                .align(Alignment.BottomEnd)
                .background(Color.Green, CircleShape)
                .border(1.dp, Color.White, CircleShape)
                .offset((-100).dp, (-100).dp)
        )
    }
}