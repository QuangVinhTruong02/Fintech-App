package com.example.fintechapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.fintechapp.common.AppImage
import com.example.fintechapp.data.response.ImageResponse

@Composable
fun CustomLoadImage(imagesUrl: List<ImageResponse>, height: Dp = 50.dp, width: Dp = 50.dp) {
    if (imagesUrl.isEmpty()) {
        Image(
            painter = painterResource(AppImage.imgNoImage),
            contentDescription = null,
            contentScale = ContentScale.Crop, // Scale ảnh theo yêu cầu
            modifier = Modifier
                .height(height)
                .width(width),
        )
    } else {
        AsyncImage(
            model = imagesUrl.first().urlImage,
            contentDescription = null,
            contentScale = ContentScale.Crop, // Scale ảnh theo yêu cầu
            modifier = Modifier
                .height(height)
                .width(width),
        )
    }
}