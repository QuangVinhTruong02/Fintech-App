package com.example.fintechapp.ui.screens.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppImage

@Composable
fun OnBoardingPage() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = 0.5f)
            .background(color = AppColor.darkBlue),
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 60.dp,
                )
        ) {
            Image(
                painter = painterResource(
                    id = AppImage.imgLogoHeader
                ),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 94.dp)
                    .size(height = 60.dp, width = 190.dp)
                    .fillMaxWidth()
            )
            Image(
                painter = painterResource(
                    id = AppImage.imgOnBoarding
                ),
                contentDescription = null,
                modifier = Modifier
                    .padding(35.dp)
                    .fillMaxSize()
            )
        }
    }


}