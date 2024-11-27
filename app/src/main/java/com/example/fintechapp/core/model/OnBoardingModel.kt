package com.example.fintechapp.core.model

import com.example.fintechapp.common.AppLanguage

data class OnboardingModel(
    val title: String,
    val description: String
)

val onBoardingModelList = listOf(
    OnboardingModel(
        title = AppLanguage.title1,
        description = AppLanguage.description1
    ),
    OnboardingModel(
        title = AppLanguage.title2,
        description = AppLanguage.description2
    ),
    OnboardingModel(
        title = AppLanguage.title1,
        description = AppLanguage.title2
    ),
)