package com.example.fintechapp.core.model

import com.example.fintechapp.common.AppLanguage

data class OnboardingModel(
    val title: String,
    val description: String
)

val onBoardingModelList = listOf(
    OnboardingModel(
        title = AppLanguage.CONVENIENT_PERSONAL_LOANS,
        description = AppLanguage.WHERE_YOU_ARE_EMPLOYED
    ),
    OnboardingModel(
        title = AppLanguage.EASY_PAYMENT_ONLINE,
        description = AppLanguage.MAKE_YOUR_PAYMENT
    ),
    OnboardingModel(
        title = AppLanguage.CONVENIENT_PERSONAL_LOANS,
        description = AppLanguage.WHERE_YOU_ARE_EMPLOYED
    ),
)