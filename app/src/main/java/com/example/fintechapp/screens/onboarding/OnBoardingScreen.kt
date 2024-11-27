package com.example.fintechapp.screens.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.core.model.onBoardingModelList
import com.example.fintechapp.screens.onboarding.components.OnBoardingContent
import com.example.fintechapp.screens.onboarding.components.OnBoardingPage
import com.example.fintechapp.screens.onboarding.components.PageIndicator
import com.example.fintechapp.screens.ulti.CustomButton

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(initialPage = 0) {
            onBoardingModelList.size
        }
        OnBoardingPage()
        Spacer(modifier = Modifier.height(32.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            PageIndicator(
                pageSize = onBoardingModelList.size,
                selectedPage = pagerState.currentPage,
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        HorizontalPager(state = pagerState) { index ->
            OnBoardingContent(onBoardingModel = onBoardingModelList[index])
        }
        Spacer(modifier = Modifier.height(48.dp))
        CustomButton(
            onClick = {},
            buttonColor = AppColor.orange,
            contentText = AppLanguage.registerNow,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(13.dp))
        CustomButton(
            onClick = {},
            buttonColor = AppColor.darkBlue,
            contentText = AppLanguage.alreadyHaveAnAccount,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        )
    }
}

