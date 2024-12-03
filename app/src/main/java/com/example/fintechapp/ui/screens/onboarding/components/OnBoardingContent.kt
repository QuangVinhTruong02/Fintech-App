package com.example.fintechapp.ui.screens.onboarding.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.core.model.OnboardingModel
import com.example.fintechapp.core.model.onBoardingModelList

@Composable
fun OnBoardingContent(onBoardingModel: OnboardingModel) {
    Column(verticalArrangement = Arrangement.Top, modifier = Modifier.fillMaxHeight(0.4f)) {
        Text(
            onBoardingModel.title,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            style = AppTextStyle.latoBoldFontStyle.copy(fontSize = 18.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            onBoardingModel.description,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            style = AppTextStyle.latoRegularFontStyle.copy(fontSize = 14.sp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun preview() {
    OnBoardingContent(onBoardingModelList[0])
}